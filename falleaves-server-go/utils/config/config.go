package config

import (
	"fmt"
	"reflect"
	"regexp"
	"errors"
	"strings"
	"strconv"
	"io/ioutil"

	yaml "gopkg.in/yaml.v2"
)

// 用于类型判断 map[interface{}]interface{}
var _typeMap    reflect.Type
// 用于类型判断
var _typeInt    reflect.Type
var _typeString reflect.Type
var _typeBool   reflect.Type
var _typeFloat  reflect.Type

func init() {
	var typeMap map[interface{}]interface{}
	_typeMap = reflect.TypeOf(typeMap)

	var typeInt int
	_typeInt = reflect.TypeOf(typeInt)

	var typeString string
	_typeString = reflect.TypeOf(typeString)

	var typeBool bool
	_typeBool = reflect.TypeOf(typeBool)

	var typeFloat float64
	_typeFloat = reflect.TypeOf(typeFloat)
}

// Config 配置文件结构体
type Config struct {
	ymalData map[string]interface{}
}

// ReadConfig 读取 yml 配置文件
func ReadConfig(file string) (*Config, error) {
	config := &Config{
		ymalData: make(map[string]interface{}),
	}

	//yamlFile, err := ioutil.ReadFile("test.yaml")
	yamlFile, err := ioutil.ReadFile(file)

	if err != nil {
        return nil, err
	}

	yDaya := make(map[interface{}]interface{})

	err = yaml.Unmarshal(yamlFile, yDaya)
    if err != nil {
        return nil, err
    }
	//log.Println("conf", config.ymalData)

	config.ymalData = getAllPath(yDaya)

	return config, nil
}

// Formatted 格式化配置文件
// 进行 节点与值 的替换
func (config *Config) Formatted() error {
	r, _ := regexp.Compile("\\${(.*?)}")
	for key, value := range config.ymalData {
		if _typeString == reflect.TypeOf(value) {
			strArray := r.FindAllString(value.(string), -1)
			var path string
			for i := 0; i < len(strArray); i++ {
				path = strings.Replace(strArray[i], "${", "", 1)
				path = strings.TrimRight(path, "}")

				if _, ok := config.ymalData[path]; ok {
					data := config.ymalData[path]
					var dataStr string
					switch data.(type) {
						case string:
							dataStr = data.(string)
							break
						case int:
							dataStr = strconv.Itoa(data.(int))
							break
						case bool:
							dataStr = strconv.FormatBool(data.(bool))
							break
						case float64:
							dataStr = strconv.FormatFloat(data.(float64), 'e', -1, 32)
							break
					}
					config.ymalData[key] = strings.Replace(config.ymalData[key].(string), strArray[i], dataStr, 1)
				} else {
					// 不存在配置文件里定义的节点，给一个小小的⚠️
					//log.Println("xxxx")
					return fmt.Errorf("found prth(%s) does not exist", path)
				}
			}
		}
	}

	return nil
}

// GetAllPath 获取所有的路径节点
func (config *Config) GetAllPath() (map[string]interface{}) {
	return config.ymalData
}

// 获取所有的路径节点
func getAllPath(mapData map[interface{}]interface{}) (map[string]interface{}) {
	pathMap := make(map[string]interface{})

	for key, value := range mapData {
		if _typeMap == reflect.TypeOf(value) {
			// 只要 递不死 就往死里归
			valueDataMap := getAllPath(value.(map[interface{}]interface{}))
			for k, v := range valueDataMap {
				pathMap[key.(string) + "." + k] = v
			}
		} else {
			pathMap[key.(string)] = value
		}
	}

	return pathMap
}

// GetData 通过节点获取配置文件的数据
func (config *Config) GetData(path string) (interface{}, error) {
	// 判断读取路径 path 是否为空
	if "" == path {
		return 0, fmt.Errorf("%s is invalid path", path)
	}
	if _, ok := config.ymalData[path]; ok {
		return config.ymalData[path], nil
	}
	return nil, fmt.Errorf("path(%s) does not exist", path)
}

// GetDataToType 通过节点获取配置文件的数据并判断类型
func (config *Config) GetDataToType(path string, typpData reflect.Type) (interface{}, error) {
	data, err := config.GetData(path)
	if err != nil {
		return 0, errors.New(fmt.Sprintf("path(%s) does not exist\n", path) + err.Error())
	}

	if typpData != reflect.TypeOf(data) {
		// 此处错误为: 获取到的数据 data 的类型不是 typpData
		return 0, fmt.Errorf("data(%s) in path(%s) is not of type %s", data, path, typpData.Name())
	}

	return data, err
}

// GetInt 通过节点获取配置文件的 int 数据
func (config *Config) GetInt(path string) (int, error) {
	data, err := config.GetDataToType(path, _typeInt)
	if err != nil {
		return 0, err
	}

	return data.(int), err
}

// GetString 通过节点获取配置文件的 string 数据
func (config *Config) GetString(path string) (string, error) {
	data, err := config.GetDataToType(path, _typeString)
	if err != nil {
		return "", err
	}

	return data.(string), err
}

// GetBool 通过节点获取配置文件的 bool 数据
func (config *Config) GetBool(path string) (bool, error) {
	data, err := config.GetDataToType(path, _typeBool)
	if err != nil {
		return false, err
	}

	return data.(bool), err
}

// GetFloat 通过节点获取配置文件的 float 数据
func (config *Config) GetFloat(path string) (float64, error) {
	data, err := config.GetDataToType(path, _typeFloat)
	if err != nil {
		return 0.0, err
	}

	return data.(float64), err
}

// SetData 通过节点设置数据
func (config *Config) SetData(path string, data interface{}) error {
	// 判断读取路径 path 是否为空
	if "" == path {
		return fmt.Errorf("%s is invalid path", path)
	}

	// TODO: 没用判断覆盖

	config.ymalData[path] = data
	return nil
}