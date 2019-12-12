# FALLEAVES 行为规范手册

![](https://basic-images-1252577698.cos.ap-shenzhen-fsi.myqcloud.com/5.jpg)



## 客户端规范

### 暂无内容



## 服务端规范

### 各个服务的端口

> #### 注册中心(Eureka)
>
> > ```
> >端口范围 8100 ~ 8199
> > 
> > - 8100
> > ```
> > 
> > - ##### 路由器(Zuul)
> >
> > ```
> >端口范围 8200 ~ 8299
> > 
> > - 8200
> > ```
> > 
> > - ##### 配置中心(Config)
> >
> > ```
> >端口范围 8300 ~ 8399
> > 
> > - 
> > ```
> > 
> > - ##### 验证服务(Auth)
> >
> > ```
> > 端口范围 9100 ~ 9199
> > 
> > - 9100
> > ```
> > 
> > - ##### 邮件服务(Mail)
> >
> > ```
> >端口范围 9800 ~ 9899
> > 
> > - 
> > ```
> > 



### 服务的API请求规则

> #### 不需要的前缀
>
> > ##### 原因
> >
> > ```
> > 该项目最终使用microservice架构, 有路由网关(Zuul or Getwary)
> > 它将帮助我们控制转发, 因此无需使用上层名字来区分服务.
> > ```
> > 
> > ##### 示例
> >
> > ```
> > ~~@RequestMapping("/auth")~~
> > public class AuthController{
> > 		
> > 	@RequestMapping("/login")
> > 	public String login(LoginVO loginVo){
> > 		...
> > 	}
> > }
> > ```
> >
>
> 
>
> #### 规定的 HTTPCode
>
> ```java
> public enum HttpCode(int codeType, int codeAppend)
> ```
> > ##### 200: 请求成功
> > 
> > 
> > ```
> > 200|1: 用户登录成功
> > 200|2: 用户注册成功
> > 200|3: 用户注销成功
> > ```
> > 
> > ##### 401: 授权的错误
> >
> > ```
> > 401|1: Identifier[登录标识符]为空
> > 401|2: Credential[登录凭据]为空
> > ```
> > 
> > 
> > ##### 500: 服务器内部错误
> >
> > ```
> > 500|1: MySQL操作超时[可能是崩溃]
> > 500|2: Reids操作超时[可能是崩溃]
> > ```
> > 
> > ##### 具体参考  [HttpCode](https://github.com/Graceyu67/Falleaves/blob/master/falleaves-server/falleaves-starter-utils/src/main/java/org/techas/falleaves/utils/ReturnValue.java)
> > 
> 
> 
> 
> #### 规定的 ResponseData
> 
> ```java
> public class ResponseData<T>(int codeType, int codeAppend, T data)
> ```
> ```java
> public class ResponseData<T>(HttpCode httpCode, T data)
> ```
> >##### Data类型
> > 
> >```
> >data类型有且仅有一个, 可以是String, Integer, Double, List<?>, Map<?, ?> ...
> >
> >Warning: 如有多个String, 请封装为List, 如有多个List, 请封装为Map
> >```
>>##### 具体参考 [ResponseData](https://github.com/Graceyu67/Falleaves/blob/master/falleaves-server/falleaves-starter-utils/src/main/java/org/techas/falleaves/utils/ResponseData.java)
> 

