# 公共模块 EUREKA-CLIENT

## 用途

>使 golang 服务能够正常注册到 spring cloud 中

## 使用

>### 创建客户端
>
>>```go
>>client, err := client.NewClient(name, port)
>>```
>>
>>`name`为服务名(为`""`时，将自动获取`application.yml`配置文件内的内容)，`port`为服务端口
>
>### 建立一个新的服务
>
>>```go
>>client.NewService(pattern, handlerService)
>>```
>>
>>`pattern`为路由地址例如 "/mail"，`handler`为处理消息的函数
>
>### 注册&注销服务
>
>>```go
>>service.Register()   // 注册
>>service.DoRegister() // 注销
>>```
>
>### 运行&停止服务
>
>>```go
>>service.Run()  // 运行服务
>>service.Stop() // 停止服务
>>```
>>
>>值得注意的是，在运行或停止服务之前，它们都将自动注册或注销服务，不过你并不需要担心是否会重复注册或注销
>
>### 运行&停止 所有服务
>
>>```go
>>client.RunAllService()  // 运行所有服务
>>client.StopAllService() // 停止所有服务
>>```
>>
>>值得注意的是，在运行或停止所有服务之前，它们都将自动注册或注销服务，相同的，你并不需要担心重复操作的问题
