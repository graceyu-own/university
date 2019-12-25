package client

import (
	"fmt"
	"net"
	"net/http"

	"Falleaves/falleaves-server-go/utils/config"
	log "Falleaves/falleaves-server-go/utils/logger"
)

const (
	_ConfigFile = "application.yml" // 配置文件名
)

// Client 注册中心客户端结构体
type Client struct {
	Name   string // 服务名称
	IPAddr string // ip
	Port   int    // 端口

	eurekaURL string // 注册地址 由 eurekaClient 提供

	serviceMap map[string]*Service // 存放服务列表
}

// NewClient 新建客户端
func NewClient(name string, port int) (*Client, error) {

	config, err := config.ReadConfig(_ConfigFile)
	if err != nil {
		return nil, err
	}
	// 格式化一下
	config.Formatted()

	if name == "" {
		name, _ = config.GetString("spring.application.name")
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

	//fmt.Printf("NewClient  %s %s %d %s\n", name, ip, port, eurekaURL)

	client := &Client{
		Name:   name,
		IPAddr: ip,
		Port:   port,

		eurekaURL: eurekaURL,

		serviceMap: make(map[string]*Service),
	}

	return client, nil
}

// NewService new HTTPService
// 这里 pattern 要带 "/" 类似 "/mail"
func (client *Client) NewService(pattern string, handler http.HandlerFunc) (*Service, error) {
	service, err := newService(client.Name, client.IPAddr, client.Port, client.eurekaURL)
	if err != nil {
		return nil, err
	}

	service.SetHandler(pattern, handler)

	err = client.AddService(service)
	if err != nil {
		return nil, err
	}

	return service, nil
}

// AddService 添加服务
func (client *Client) AddService(service *Service) error {
	pattern := service.GetPattern()
	if _, ok := client.serviceMap[pattern]; ok {
		// 客户端中已经存在 pattern 这个服务
		return fmt.Errorf("service(%s) is already in the client(%s)", pattern, client.Name)
	}
	client.serviceMap[pattern] = service
	return nil
}

// RunAllService 运行所有服务
func (client *Client) RunAllService() {
	for _, value := range client.serviceMap {
		go value.Run()
	}
}

// StopAllService 停止所有服务
func (client *Client) StopAllService() {
	for _, value := range client.serviceMap {
		err := value.Stop()
		if err != nil {
			log.Warning(err.Error())
		}
	}
}
