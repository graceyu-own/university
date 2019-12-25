# 公共模块 CONFIG

## 用途

>通过对 [gopkg.in/yaml](https://github.com/go-yaml/yaml) 进行重新封装
>使 golang 服务能够正常识别类似 spring 的配置文件

## 使用

### 读取 yaml

``` go
import "Falleaves/falleaves-server-go/utils/config"

con, err := config.ReadConfig("test.yaml")
if err != nil {
    log.Fatal(err)
}
```

### 格式化数据

``` go
err := con.Formatted()
if err != nil {
    log.Fatal(err)
}
```

### 读取数据

``` go
data, err := con.GetString("eureka.client.service-url.defaultZone")
if err != nil {
    log.Fatal(err)
}
log.Println(data)
```

### 写入数据

``` go
err := con.SetData("eureka.client.service-url.defaultZone", "http://10.0.0.1:8100/eureka")
if err != nil {
    log.Fatal(err)
}
```
