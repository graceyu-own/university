package client

import (
	"io/ioutil"
	"os"
	"os/signal"

	"net/http"

	"bytes"
	"fmt"
	"strconv"
	"time"

	log "Falleaves/falleaves-server-go/utils/logger"
)

const (
	_RegisterURL   = "%sapps/%s"
	_DoRegisterURL = "%sapps/%s/%s"
)

// Service 用于存放http服务的配置
type Service struct {
	Server  http.Server
	Handler http.HandlerFunc

	Data RegisterData

	eurekaURL string // 注册地址 由 eurekaClient 提供
	pattern   string // 路由地址，默认为 服务名  这里要带 "/" 类似 "/mail"

	isRegister bool // 是否已经注册
}

// NewService new HTTPService
func newService(name string, ip string, port int, url string) (*Service, error) {
	service := &Service{
		eurekaURL: url,
	}
	service.Data = *NewRegisterData(name, ip, port).SetBaseData()

	return service, nil
}

// GetPattern 获取路由地址
func (service *Service) GetPattern() string {
	if service.pattern == "" {
		//service.SetPattern("/" + service.GetName())
		service.setPattern(service.GetName())
	}
	return service.pattern
}

// SetPattern 设置路由地址
func (service *Service) setPattern(pattern string) *Service {
	service.pattern = pattern
	return service
}

// GetName 获取服务名字
func (service *Service) GetName() string {
	return service.Data.Instance.App
}

// GetIPAddr 获取服务IP
func (service *Service) GetIPAddr() string {
	return service.Data.Instance.IPAddr
}

// GetPort 获取服务端口
func (service *Service) GetPort() int {
	return service.Data.Instance.Port.Port
}

// GetInstanceID 获取唯一标识符
func (service *Service) GetInstanceID() string {
	return service.Data.Instance.InstanceID
}

// IsRegister 服务是否注册
func (service *Service) IsRegister() bool {
	return service.isRegister
}

func (service *Service) String() string {
	return fmt.Sprintf("HTTPService[Name: %s, Port: %d, RegisterData: %s]",
		service.GetName(), service.GetPort(), service.Data.String())
}

// Register 注册服务
func (service *Service) Register() error {

	if service.IsRegister() {
		return fmt.Errorf("service(%s) is registered", service.GetName())
	}
	service.isRegister = true

	httpClient := &http.Client{}
	// 生成Request对象
	jsonByte, err := service.Data.ToJSONByte()
	if err != nil {
		return err
	}
	//req, err := http.NewRequest("POST", service.eurekaURL, bytes.NewBuffer(jsonByte))
	req, err := http.NewRequest("POST", service.getRegisterURL(), bytes.NewBuffer(jsonByte))
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
		log.Debug("service(%s) register success", service.GetName())
		break
	default:
		body, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			return fmt.Errorf("register service failed. status %d. error data: \"%s\"", resp.StatusCode, err)
		}
		return fmt.Errorf("register service failed. status %d. error data: \"%s\"", resp.StatusCode, string(body))
	}

	return nil
}

// DeRegister 注销服务
func (service *Service) DeRegister() error {

	if service.IsRegister() {
		// 少用 !=
	} else {
		return fmt.Errorf("service(%s) useless registration", service.GetName())
	}
	service.isRegister = false

	httpClient := &http.Client{}
	req, err := http.NewRequest("DELETE", service.getDoRegisterURL(), nil)
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

	if resp.StatusCode == 200 {
		log.Debug("service(%s) de-register success", service.GetName())
	} else {
		body, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			return fmt.Errorf("de-register service failed. status %d. error data: \"%s\"", resp.StatusCode, err)
		}
		return fmt.Errorf("de-register service failed. status %d. error data: \"%s\"", resp.StatusCode, string(body))
	}

	return nil
}

// SetHandler 设置消息处理者
func (service *Service) SetHandler(pattern string, handler http.HandlerFunc) *Service {

	service.setPattern(pattern)

	mux := http.NewServeMux()
	// 这里要带 "/" 类似 "/mail"
	mux.HandleFunc(service.GetPattern(), handler)

	// 添加 info
	service.Data.Instance.HomePageURL = fmt.Sprintf("http://%s:%d%s/info",
						service.GetIPAddr(), service.GetPort(), service.GetPattern())

	service.Server = http.Server{
		Addr:         ":" + strconv.Itoa(service.GetPort()),
		WriteTimeout: time.Second * 5, //超时时间  5s
		Handler:      mux,
	}

	return service
}

// Run 运行服务
func (service *Service) Run() {
	service.Register()
	service.runHTTP()
}

func (service *Service) runHTTP() {
	// 一个通知退出的chan通道
	exit := make(chan os.Signal)
	signal.Notify(exit, os.Interrupt)

	go func() {
		// 接收退出信号
		<-exit
		if err := service.Server.Close(); err != nil {
			log.Fatal("http service close:", err)
		}
	}()

	log.Debug("http service started successfully\n%s", service.String())
	err := service.Server.ListenAndServe()
	if err != nil {
		// 正常退出
		if err == http.ErrServerClosed {
			log.Fatal("service(%s) closed under request", service.GetName())
		} else {
			log.Fatal("service(%s) closed unexpected", service.GetName(), err)
		}
	}
	log.Info("service(%s) exited", service.GetName())
}

// Stop 关闭服务
func (service *Service) Stop() error {
	err := service.DeRegister()
	if err != nil {
		return err
	}
	return service.Server.Shutdown(nil)
}

func (service *Service) getRegisterURL() string {
	return fmt.Sprintf(_RegisterURL, service.eurekaURL, service.GetName())
}

func (service *Service) getDoRegisterURL() string {
	return fmt.Sprintf(_DoRegisterURL, service.eurekaURL, service.GetName(), service.GetInstanceID())
}
