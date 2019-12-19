package main

import (
	"io/ioutil"
	"fmt"
	"log"
	"reflect"
	"errors"
	"strings"

	yaml "gopkg.in/yaml.v2"
)


// 用于类型判断 map[interface{}]interface{}
var _typeMap_    reflect.Type
// 用于类型判断
var _typeInt_    reflect.Type
var _typeString_ reflect.Type
var _typeBool_   reflect.Type
var _typeFloat_  reflect.Type

func init() {
	var typeMap map[interface{}]interface{}
	_typeMap_ = reflect.TypeOf(typeMap)

	var typeInt int
	_typeInt_ = reflect.TypeOf(typeInt)

	var typeString string
	_typeString_ = reflect.TypeOf(typeString)

	var typeBool bool
	_typeBool_ = reflect.TypeOf(typeBool)

	var typeFloat float64
	_typeFloat_ = reflect.TypeOf(typeFloat)
}

type Config struct {
	ymalData map[interface{}]interface{}
}

// 读取 yml 配置文件
func ReadConfig(file string) (*Config, error) {
	config := &Config{
		ymalData: make(map[interface{}]interface{}),
	}

	yamlFile, err := ioutil.ReadFile(file)//ioutil.ReadFile("test.yaml")

	if err != nil {
        return nil, err
    }

	err = yaml.Unmarshal(yamlFile, config.ymalData)
    if err != nil {
        return nil, err
    }
	//log.Println("conf", config.ymalData)

	return config, nil
}

// 格式化配置文件
// 进行 节点 值 替换
// TODO 未完成
func (config *Config) Formatted() *Config {

	for key, value := range config.ymalData {
		//a := reflect.TypeOf(value) == reflect.TypeOf(b)
		
		log.Println(key, reflect.TypeOf(value) == _typeMap_)
		if _typeMap_ == reflect.TypeOf(value){
			
		}
		
	}

	return config
}

// 通过节点获取配置文件的数据
func (config *Config) GetData(path string) (interface{}, error) {
	// 判断读取路径 path 是否为空
	if "" == path {
		return 0, errors.New(fmt.Sprintf("%s is invalid path.", path))
	}
	// 使用 "." 进行路径分割
	pathArray := strings.Split(path, ".")

	var mapData map[interface{}]interface{}
	var data interface{}
	var err error = nil

	// 由于循环需要 mapData 有值，所以在循环外进行第一次 get 数据，使 mapData 初始化值
	data, mapData, err = getData(config.ymalData, pathArray[0])
	if err != nil {
		// 此处错误为: path 中的 pathArray[0] 这个 key 不存在
		return 0, errors.New(fmt.Sprintf("Key(%s) in path does not exist\n", pathArray[0]) + err.Error())
	}

	for i := 1; i < len(pathArray); i++ {
		// 递归获取 data, mapData
		data, mapData, err = getData(mapData, pathArray[i])
		if err != nil {
			// 同上一个错误 pathArray[i]
			return 0, errors.New(fmt.Sprintf("Key(%s) in path does not exist\n", pathArray[i]) + err.Error())
		}
	}

	return data, err
}

// 获取数据 内部函数 用于 GetData 的递归
func getData(mapData map[interface{}]interface{}, key string) (interface{}, map[interface{}]interface{}, error) {
	if nil != mapData && _typeMap_ != reflect.TypeOf(mapData) {
		// 此处错误为: 参数 mapData 不是有效的 map
		return 0, nil, errors.New("Parameter mapData not is a valid map[interface{}]interface{}.")
	}

	if _, ok := mapData[key]; ok {
	} else {
		return 0, nil, errors.New(fmt.Sprintf("Parameter mapData does not exist key(%s)", key))
	}
	data := mapData[key]

	// 判断获取的数据 data 是否有有效的 map
	if _typeMap_ == reflect.TypeOf(data) {
		return nil, data.(map[interface{}]interface{}), nil
	}
	return data, nil, nil
}

// 通过节点获取配置文件的数据并判断类型
func (config *Config) GetDataToType(path string, typpData reflect.Type) (interface{}, error) {
	data, err := config.GetData(path)
	if err != nil {
		return 0, errors.New(fmt.Sprintf("Path(%s) does not exist\n", path) + err.Error())
	}

	if typpData != reflect.TypeOf(data) {
		// 此处错误为: 获取到的数据 data 的类型不是 typpData
		return 0, errors.New(fmt.Sprintf("Data(%s) in path(%s) is not of type %s\n", data, path, typpData.Name()))
	}

	return data, err
}

// 通过节点获取配置文件的 int 数据
func (config *Config) GetInt(path string) (int, error) {
	data, err := config.GetDataToType(path, _typeInt_)
	if err != nil {
		return 0, err
	}

	return data.(int), err
}

// 通过节点获取配置文件的 string 数据
func (config *Config) GetString(path string) (string, error) {
	data, err := config.GetDataToType(path, _typeString_)
	if err != nil {
		return "", err
	}

	return data.(string), err
}

// 通过节点获取配置文件的 bool 数据
func (config *Config) GetBool(path string) (bool, error) {
	data, err := config.GetDataToType(path, _typeBool_)
	if err != nil {
		return false, err
	}

	return data.(bool), err
}

// 通过节点获取配置文件的 float 数据
func (config *Config) GetFloat(path string) (float64, error) {
	data, err := config.GetDataToType(path, _typeFloat_)
	if err != nil {
		return 0.0, err
	}

	return data.(float64), err
}

func main() {
	//ReadConfig("test.yaml")
	con, _ := ReadConfig("test.yaml")
	//con.Formatted()
	data, err := con.GetFloat("test.float")
	if err != nil {
		log.Fatal(err)
	}
	log.Print(data)
}