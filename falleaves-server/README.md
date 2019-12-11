<h2 align="center">FALLEAVES</h2>
> <p style="font-size: 18px;">各个服务的端口</p>
>
> > - 注册中心(Eureka)
> >
> > ```
> > 端口范围 8100 ~ 8199
> > 
> > - 8100
> > ```
> >
> > - 路由器(Zuul)
> >
> > ```
> > 端口范围 8200 ~ 8299
> > 
> > - 8200
> > ```
> >
> > - 配置中心(Config)
> >
> > ```
> > 端口范围 8300 ~ 8399
> > 
> > - 
> > ```
> >
> > - 自定义的服务(Define Services)
> >
> > > 验证服务(Auth)
> > >
> > > ```
> > > 端口范围 9100 ~ 9199
> > > 
> > > - 9100
> > > ```
> > >
> > > 邮件服务(Mail)
> > >
> > > ```
> > > 端口范围 9800 ~ 9899
> > > 
> > > - 
> > > ```
> > >
> > > 

> <p style="font-size: 18px;">服务的API请求规则</p>
>
> > 不需要的前缀
> >
> > ```java
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
> > 规定的HTTP相应Code
> >
> > > 200x: 请求成功
> > >
> > > ```
> > > 200.1: 用户登录成功
> > > 200.2: 用户注册成功
> > > 200.3: 用户注销成功
> > > 200.9: 邮件发送成功 
> > > ```
> >
> > > 400x: 错误的请求
> > >
> > > ```
> > > NULL
> > > ```
> >
> > > 401x: 授权的错误
> > >
> > > ```
> > > 401.1: Identifier[登录标识符]为空
> > > 401.2: Credential[登录凭据]为空
> > > 401.3: IncorrectCredentials[错误的登录凭据]
> > > ```
> > >
> >
> > > 500x: 服务器内部错误
> > >
> > > ```
> > > 500.1: MySQL操作超时[可能是崩溃]
> > > 500.2: Reids操作超时[可能是崩溃]
> > > ```
> >
> > > 具体参照  [ReturnValue](https://github.com/Graceyu67/Falleaves/blob/master/falleaves-server/falleaves-starter-utils/src/main/java/org/techas/falleaves/utils/ReturnValue.java)
> >
> > 返回的类型
> > >
> > >通用的返回类型使用Code + Data
> > >
> > >```kotlin
> > >class ResponseData(code: double, data: T) 
> > >```
> > >
> > >Data类型
> > >
> > >```
> > >data类型有且仅有一个, 可以是String, Integer, Double, List<?>, Map<?, ?> ...
> > >
> > >Warning: 如有多个String, 请封装为List, 如有多个List, 请封装为Map
> > >```
> > >具体参考 [ResponseData](https://github.com/Graceyu67/Falleaves/blob/master/falleaves-server/falleaves-starter-utils/src/main/java/org/techas/falleaves/utils/ResponseData.java)