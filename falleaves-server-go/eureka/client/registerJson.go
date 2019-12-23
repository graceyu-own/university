package client

import (
	"fmt"

	"encoding/json"

	log "Falleaves/falleaves-server-go/utils/logger"
)

const (
	_ActionTypeAdded    = "ADDED"    // 添加
	_ActionTypeModified = "MODIFIED" // 修改
	_ActionTypeDeleted  = "DELETED"  // 删除
)

var _DataCenterInfo map[string]string

func init() {
	_DataCenterInfo = map[string]string{
		"@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
		"name":   "MyOwn",
	}
}

// RegisterData e
type RegisterData struct {
	Instance instance `json:"instance"`
}

type instance struct {
	App      string   `json:"app"`      // eureka服务上注册的应用程序的名字，默认为 unknow
	IPAddr   string   `json:"ipAddr"`   // ip地址
	Port     portData `json:"port"`     // 端口
	HostName string   `json:"hostName"` // 主机名应该是公共dns名称，在ec2中，公共dns名称将始终解析为其私有IP

	InstanceID       string                 `json:"instanceId,omitempty"`       // 此实例相关联的主机名，是其他实例可以用来进行请求的准确名称。默认为 ipAddr:app:port
	Status           string                 `json:"status,omitempty"`           // 状态 默认 UP
	AppGroupName     string                 `json:"appGroupName,omitempty"`     // eureka服务上注册的应用程序组的名字，默认为 等于 instance.app
	VipAddress       string                 `json:"vipAddress,omitempty"`       // 此客户端只对一个单一的VIP注册表的信息感兴趣。默认为 null
	SecureVipAddress string                 `json:"secureVipAddress,omitempty"` // 安全的 VIP ?
	HomePageURL      string                 `json:"homePageUrl,omitempty"`      // 实例的绝对主页URL路径，为其他服务提供信息时使用的路径,默认为 null
	StatusPageURL    string                 `json:"statusPageUrl,omitempty"`    // 实例绝对状态页URL路径，为其他服务提供信息时来找到这个实例的状态的路径，默认为 null
	HealthCheckURL   string                 `json:"healthCheckUrl,omitempty"`   // 获取此实例的绝对健康检查URL路径，默认为 null
	DataCenterInfo   map[string]string      `json:"dataCenterInfo,omitempty"`   // __DATACENTERINFO
	LeaseInfo        leaseInfo              `json:"leaseInfo,omitempty"`        // 可选
	MetaData         map[string]interface{} `json:"metadata,omitempty"`         // 可选的特定于应用程序的元数据

	SecureHealthCheckURL          string `json:"secureHealthCheckUrl,omitempty"`          // 实例的绝对安全网页的URL路径，默认为 null
	IsCoordinatingDiscoveryServer bool   `json:"isCoordinatingDiscoveryServer,omitempty"` // TODO: 我也不知道这是什么 反正填 false
	LastUpdatedTimestamp          int    `json:"lastUpdatedTimestamp,omitempty"`          // 最近更新的时间戳
	LastDirtyTimestamp            int    `json:"lastDirtyTimestamp,omitempty"`            //
	ActionType                    string `json:"actionType,omitempty"`                    // 动作类型 __ACTION_TYPE_ADDED, MODIFIED, DELETED 默认为 ADDED
	OverriddenStatus              string `json:"overriddenStatus,omitempty"`              // 覆写状态 默认为 UNKNOWN

	// sid string
	// asgName string
	// countryId int
}

type portData struct {
	Enabled bool `json:"@enabled,string"` // true
	Port    int  `json:"$"`               // 端口
}

type leaseInfo struct {
	RenewalIntervalInSecs int   `json:"renewalIntervalInSecs,omitempty"` // 默认30秒续约一次
	DurationInSecs        int   `json:"durationInSecs,omitempty"`        // 默认90秒没有收到客户端续约，将其从注册列表中删除
	RegistrationTimestamp int64 `json:"registrationTimestamp,omitempty"` // 注册时间戳      毫秒
	LastRenewalTimestamp  int64 `json:"lastRenewalTimestamp,omitempty"`  // 最后续订时间戳
	EvictionTimestamp     int64 `json:"evictionTimestamp,omitempty"`     // TODO: 我也不知道这是什么
	ServiceUpTimestamp    int64 `json:"serviceUpTimestamp,omitempty"`    // 服务启动时间戳
}

// NewRegisterData 用于创建 RegisterData
func NewRegisterData(app string, ipAddr string, port int) *RegisterData {

	portData := portData{
		Enabled: true,
		Port:    port,
	}

	data := &RegisterData{
		Instance: instance{
			App:      app,
			IPAddr:   ipAddr,
			Port:     portData,
			HostName: app,

			DataCenterInfo: _DataCenterInfo,
		},
	}

	data.Instance.InstanceID =
		fmt.Sprintf("%s:%s:%d", data.Instance.HostName, data.Instance.App, data.Instance.Port.Port)

	return data
}

// SetBaseData 自动设置一些基础值
func (data *RegisterData) SetBaseData() *RegisterData {

	data.Instance.AppGroupName = data.Instance.App
	data.Instance.LeaseInfo = leaseInfo{
		RenewalIntervalInSecs: 30,
		DurationInSecs:        90,
	}

	return data
}

// ToJSONByte 转换为json的Byte数据
func (data *RegisterData) ToJSONByte() ([]byte, error) {
	//jsonByte, err := json.MarshalIndent(data, "", "    ")
	jsonByte, err := json.Marshal(data)
	if err != nil {
		return nil, err
	}
	return jsonByte, nil
}

// ToJSON 转换为json
func (data *RegisterData) ToJSON() (string, error) {
	jsonByte, err := data.ToJSONByte()
	if err != nil {
		return "", err
	}
	return string(jsonByte), nil
}

func (data *RegisterData) String() string {
	json, err := data.ToJSON()
	if err != nil {
		log.Warning(err.Error())
		return ""
	}
	return json
}