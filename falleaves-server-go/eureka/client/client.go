package client

import (
	"io/ioutil"
	"os"
	"os/signal"

	"net/http"

	"bytes"
	"fmt"
	"net"
	"strconv"
	"time"

	"Falleaves/falleaves-server-go/utils/config"
	log "Falleaves/falleaves-server-go/utils/logger"
)

const (
	_RegisterURL   = "%sapps/%s"
	_DoRegisterURL = "%sapps/%s/%s"
)

// Client 用于存放http服务的配置
type Client struct {
	Server  http.Server
	Handler http.HandlerFunc

	Data RegisterData

	eurekaURL string // 注册地址 由 eurekaClient 提供
	pattern   string // 路由地址，默认为 服务名

	isRegister bool // 是否已经注册
}

// NewClient new HTTPService
func NewClient(name string, port int) (*Client, error) {
	config, err := config.ReadConfig("application.yml")
	if err != nil {
		return nil, err
	}

	if name == "" {
		name, _ = config.GetString("spring.application")
		port, _ = config.GetInt("server.port")
	}
	eurekaURL, _ := config.GetString("eureka.client.service-url.defaultZone")

	addrs, err := net.InterfaceAddrs()
	if err != nil {
		return nil, err
	}

	// 取 IP
	var ip string
	for _, address := range addrs {
		if ipnet, ok := address.(*net.IPNet); ok && !ipnet.IP.IsLoopback() {
			if ipnet.IP.To4() != nil {
				ip = ipnet.IP.String()
				break
			}
		}
	}

	client := &Client{
		eurekaURL: eurekaURL,
	}
	client.Data = *NewRegisterData(name, ip, port).SetBaseData()

	return client, nil
}

// GetPattern 获取路由地址
func (client *Client) GetPattern() string {
	if client.pattern == "" {
		client.SetPattern(client.GetName())
	}
	return client.pattern
}

// SetPattern 设置路由地址
func (client *Client) SetPattern(pattern string) *Client {
	client.pattern = pattern
	return client
}

// GetName 获取服务名字
func (client *Client) GetName() string {
	return client.Data.Instance.App
}

// GetIPAddr 获取服务IP
func (client *Client) GetIPAddr() string {
	return client.Data.Instance.IPAddr
}

// GetPort 获取服务端口
func (client *Client) GetPort() int {
	return client.Data.Instance.Port.Port
}

// GetInstanceID 获取唯一标识符
func (client *Client) GetInstanceID() string {
	return client.Data.Instance.InstanceID
}

// IsRegister 服务是否注册
func (client *Client) IsRegister() bool {
	return client.isRegister
}

func (client *Client) String() string {
	return fmt.Sprintf("HTTPService[Name: %s, Port: %d, RegisterData: %s]",
		client.GetName(), client.GetPort(), client.Data.String())
}

// Register 注册服务
func (client *Client) Register() error {

	if client.IsRegister() {
		return fmt.Errorf("service(%s) is registered", client.GetName())
	}
	client.isRegister = true

	httpClient := &http.Client{}
	// 生成Request对象
	jsonByte, err := client.Data.ToJSONByte()
	if err != nil {
		return err
	}
	//req, err := http.NewRequest("POST", client.eurekaURL, bytes.NewBuffer(jsonByte))
	req, err := http.NewRequest("POST", client.getRegisterURL(), bytes.NewBuffer(jsonByte))
	if err != nil {
		return err
	}
	// 添加Header
	req.Header.Add("Content-Type", "application/json")
	req.Header.Add("Accept", "application/json")
	// 发起请求
	resp, err := httpClient.Do(req)
	if err != nil {
		return err
	}
	// 设定关闭响应体
	defer resp.Body.Close()

	switch resp.StatusCode {
	case 204:
		log.Debug("服务注册成功")
		break
	default:
		body, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			return fmt.Errorf("register client failed. status %d. error data: \"%s\"", resp.StatusCode, err)
		}
		return fmt.Errorf("register client failed. status %d. error data: \"%s\"", resp.StatusCode, string(body))
	}

	return nil
}

// DeRegister 注销服务
func (client *Client) DeRegister() error {

	if client.IsRegister() {
		// 少用 !=
	} else {
		return fmt.Errorf("service(%s) useless registration", client.GetName())
	}
	client.isRegister = false

	httpClient := &http.Client{}
	req, err := http.NewRequest("POST", client.getDoRegisterURL(), nil)
	if err != nil {
		return err
	}
	// 添加Header
	req.Header.Add("Content-Type", "application/json")
	req.Header.Add("Accept", "application/json")
	// 发起请求
	resp, err := httpClient.Do(req)
	if err != nil {
		return err
	}
	// 设定关闭响应体
	defer resp.Body.Close()

	switch resp.StatusCode {
	case 200:
		log.Debug("服务注销成功")
		break
	default:
		body, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			return fmt.Errorf("de-register client failed. status %d. error data: \"%s\"", resp.StatusCode, err)
		}
		return fmt.Errorf("de-register client failed. status %d. error data: \"%s\"", resp.StatusCode, string(body))
	}

	return nil
}

// SetHandler 设置消息处理者
func (client *Client) SetHandler(handler http.HandlerFunc) *Client {
	mux := http.NewServeMux()
	mux.HandleFunc("/"+client.GetPattern(), handler)

	client.Data.Instance.HomePageURL =
		fmt.Sprintf("http://%s:%d/%s", client.GetIPAddr(), client.GetPort(), client.GetPattern())

	client.Server = http.Server{
		Addr:         ":" + strconv.Itoa(client.GetPort()),
		WriteTimeout: time.Second * 5, //超时时间  5s
		Handler:      mux,
	}

	return client
}

// Run 运行服务
func (client *Client) Run() {
	go client.run()
	client.Register()
}

func (client *Client) run() {
	// 一个通知退出的chan
	exit := make(chan os.Signal)
	signal.Notify(exit, os.Interrupt)

	go func() {
		// 接收退出信号
		<-exit
		if err := client.Server.Close(); err != nil {
			log.Fatal("HTTPService Close:", err)
		}
	}()

	log.Debug("HTTPService started successfully", client.String())
	err := client.Server.ListenAndServe()
	if err != nil {
		// 正常退出
		if err == http.ErrServerClosed {
			log.Fatal("Service(%s) closed under request", client.GetName())
		} else {
			log.Fatal("Service(%s) closed unexpected", client.GetName(), err)
		}
	}
	log.Info("Service(%s) exited", client.GetName())
}

// Stop 关闭服务
func (client *Client) Stop() error {
	err := client.DeRegister()
	if err != nil {
		return err
	}
	return client.Server.Shutdown(nil)
}

func (client *Client) getRegisterURL() string {
	return fmt.Sprintf(_RegisterURL, client.eurekaURL, client.GetName())
}

func (client *Client) getDoRegisterURL() string {
	return fmt.Sprintf(_DoRegisterURL, client.eurekaURL, client.GetName(), client.GetInstanceID())
}
