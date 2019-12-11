## <center>FALLEAVES PROJECT</center>

> <p style="font-size: 15px;">服务的API请求规则</p>
> > 不需要的前缀
> >
> >     ~~@RequestMapping("/auth")~~
> >		public class AuthController{
> >		
> >			@RequestMapping("/login")
> >			public String login(LoginVO loginVo){
> >				...
> >			}
> >		}
> > 规定的HTTP相应Code
> >
> > > 200x: 请求成功
> >
> > >     200.1: 用户登录成功
> > >     200.2: 用户注册成功
> > >     200.3: 用户注销成功
> > >     200.9: 邮件发送成功
> >
> > > 400x: 错误的请求
> > >
> > >     NULL
> >
> > > 401x: 授权的错误
> > > 
> > >     401.1: Identifier[登录标识符]为空
> > >     401.2: Credential[登录凭据]为空
> > >     401.3: IncorrectCredentials[错误的登录凭据]
> >
> > > 500x: 服务器内部错误
> > > 
> > >     500.1: MySQL操作超时[可能是崩溃]
> > >     500.2: Reids操作超时[可能是崩溃]
> > 
> > > 具体参照 [org.techas.falleaves.utils.ReturnValue]
> > 
> > 返回的类型
> > > 
> > > 通用的返回类型使用Code + Data
> > >
> > >     class ResponseData(code: double, data: <T extends Objcet>) 
> > > 
> > > Data类型
> > >      
> > >     data类型有且仅有一个, 可以是String, Integer, Double, List<?>, Map<?, ?> ...
> > >
> > >     # Warning: 如有多个String, 请封装为List, 如有多个List, 请封装为Map
