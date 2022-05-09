# ILocker
整体向右移动：Tab键 （tab键长这样⇥）
整体向左移动：shift+Tab （shift键长这样⇧）
整体向上移动：control+command+向上箭头
整体向下移动：control+command+向下箭头

@PostMapping和@PutMapping区别
@PostMapping和@PutMapping作用等同，都是用来向服务器提交信息。
如果是添加信息，倾向于用@PostMapping，如果是更新信息，倾向于用@PutMapping。两者差别不是很明显。
## Lombok 简化代码

@Data：作用于类上，是以下注解的集合：@Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor
@Getter/@Setter: 作用类上，生成所有成员变量的getter/setter方法；作用于成员变量上，生成该成员变量的getter/setter方法。可以设定访问权限及是否懒加载等。  
@ToString：作用于类，覆盖默认的toString()方法，可以通过of属性限定显示某些字段，通过exclude属性排除某些字段。  
@EqualsAndHashCode：作用于类，覆盖默认的equals和hashCode  
@RequiredArgsConstructor：生成包含final和@NonNull注解的成员变量的构造器；

@NonNull：主要作用于成员变量和参数中，标识不能为空，否则抛出空指针异常。  
@NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor：
作用于类上，用于生成构造函数。有staticName、access等属性。staticName属性一旦设定，将采用静态方法的方式生成实例，access属性可以限定访问权限。    
@NoArgsConstructor：生成无参构造器；
@AllArgsConstructor：生成全参构造器   
@Builder：作用于类上，将类转变为建造者模式  
@Log：作用于类上，生成日志变量。针对不同的日志实现产品，有不同的注解：  
@Cleanup：自动关闭资源，针对实现了java.io.Closeable接口的对象有效，如：典型的IO流对象  
@SneakyThrows：可以对受检异常进行捕捉并抛出，可以改写上述的main方法如下  
@Synchronized：作用于方法级别，可以替换synchronize关键字或lock锁，用处不大.

##swagger2 生成使用文档和API接口文档
swagger2常用注解说明@Api
https://blog.csdn.net/qq_32534441/article/details/89922363
Swagger2 最全注解说明
https://www.h3399.cn/201911/733694.html


@Api：修饰整个类，描述Controller的作用  
@ApiOperation：描述一个类的一个方法，或者说一个接口  
@ApiParam：单个参数描述  
@ApiModel：用对象实体来作为入参  
@ApiProperty：用对象接实体收参数时，描述对象的一个字段  
@ApiResponse：HTTP响应其中1个描述  
@ApiResponses：HTTP响应整体描述  
@ApiIgnore：使用该注解忽略这个API  
@ApiError ：发生错误返回的信息  
@ApiImplicitParam：一个请求参数  
@ApiImplicitParams： 多个请求参数

@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”）  
@ApiParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”,dateType="变量类型”,paramType="请求方式”）  
@ApiImplicitParam(required = “是否必须参数”, name = “参数名称”, value = “参数具体描述”,dateType="变量类型”,paramType="请求方式”）

### Spring security
Spring Security 基本使用  
https://www.cnblogs.com/yz-yang/p/11697942.html

深入浅出Spring Security（一）：三句话解释框架原理  
https://blog.csdn.net/zimou5581/article/details/102457672

*史上最简单的Spring Security教程：终极篇，注意，这不是终点，不是最后一篇！！！  
https://blog.csdn.net/liuminglei1987/article/details/107538666

BCryptPasswordEncoder  
spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密。  
SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的。

1）加密(encode)：注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。  
2）密码匹配(matches)：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），
而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确。

configure(HttpSecurity http)：http request的相关配置  
formLogin()：表单登陆支持  
httpBasic()：配置Http Basic验证  
cors()：配置跨域  
sessionManagement()：session配置  
authorizeRequests()：访问授权配置  
csrf()：添加 CSRF 支持  
addFilterBefore()：添加过滤器在指定过滤器前  
addFilterAt()：添加过滤器替换指定过滤器  
addFilterAfter()：添加过滤器在指定过滤器后  
logout()：添加退出登录支持  
等等。。。。

5、 如何在Controller注入我们需要的信息  
https://juejin.cn/post/6844904142037581831  
注入Principal获取登录时候的用户名  
注入UserDetails获取数据中的UserDetails特有信息  
注入Authentication获取更多的信息

### JWT -- JSON WEB TOKEN
Token的详细说明，看这一篇就够了  
链接：https://www.jianshu.com/p/1422374a404b

1.header(头部)，头部信息主要包括（参数的类型--JWT,签名的算法--HS256）  
2.poyload(负荷)，负荷基本就是自己想要存放的信息(因为信息会暴露，不应该在载荷里面加入任何敏感的数据)  
3.sign(签名)，签名的作用就是为了防止恶意篡改数据，下边会详细说明
*  token的claim里中的字段
*     iss：Issuer，发行者    -- token的发行者  
*     sub：Subject，主题     -- 该JWT所面向的用户  
*     aud：Audience，观众    -- 接收该JWT的一方  
*     exp：Expiration time  -- token的失效时间  
*     nbf：Not before       -- 在此时间段之前,不会被处理  
*     iat：Issued at Time   -- jwt发布时间  
*     jti：JWT ID           -- jwt唯一标识,防止重复使用 

### 1.2 RSA加密原理
RSA加密解密实现  
https://blog.csdn.net/hustpzb/article/details/72734578  
RSA公钥私钥提取 N、e、d这三个值  
原文地址： https://www.cnblogs.com/AloneSword/p/3326750.html

RSA是目前最有影响力的公钥加密算法，该算法基于一个十分简单的数论事实：
将两个大素数相乘十分容易，但那时想要对其乘积进行因式分解却极其困难，因此可以将乘积公开作为加密密钥，即公钥，而两个大素数组合成私钥。
公钥是可发布的供任何人使用，私钥则为自己所有，供解密之用。
解密者拥有私钥，并且将由私钥计算生成的公钥发布给加密者。加密都使用公钥进行加密，并将密文发送到解密者，解密者用私钥解密将密文解码为明文。

        以甲要把信息发给乙为例，首先确定角色：甲为加密者，乙为解密者。首先由乙随机确定一个KEY，称之为密匙，将这个KEY始终保存在机器B中而不发出来；然后，由这个 KEY计算出另一个KEY，称之为公匙。这个公钥的特性是几乎不可能通过它自身计算出生成它的私钥。接下来通过网络把这个公钥传给甲，甲收到公钥后，利用公钥对信息加密，并把密文通过网络发送到乙，最后乙利用已知的私钥，就对密文进行解码了。以上就是RSA算法的工作流程。
算法实现过程为：
1. 随意选择两个大的质数p和q，p不等于q，计算N=pq。
2. 根据欧拉函数，不大于N且与N互质的整数個数為(p-1)(q-1)。
3. 选择一个整数e与(p-1)(q-1)互质，并且e小于(p-1)(q-1)。
4. 用以下这个公式计算d：d× e ≡ 1 (mod (p-1)(q-1))。
5. 将p和q的记录销毁。
   以上内容中，(N,e)是公钥，(N,d)是私钥。

BaseCode64 加密原理
* encodeBase64会对字符串3位一组自动补全，因而最后可能会出现 == 或者 =
* 而encodeBase64URLSafe则是按照字符串实际位数进行加密，最后若为1位，则不补全，不会出现 == 或者 =

* encodeBase64URLSafeString是说编码后在URL中使用是安全的，
* 所谓安全是这种方式编码的时候会替换掉64个字符中的 ”+“替换成"-"，"/"替换成"_" ,"="替换成"" ，
* 因为这两个字符在URL中有特殊含义，使用会出问题，也就是”不安全“，还有Base64本身和信息上的安全没有半点关系，


### rabbitMQ (Message Queue)

### 
spring security 认证过程
JwtAuthenticationTokenFilter --> FilterInvocationSecurityMetadataSource-->AccessDecisionManager


### 
@RequestBody和@RequestParam用法总结
https://www.cnblogs.com/blackblack/p/13904187.html

总结
Post请求接收@RequestBody修饰的参数和@RequestParam修饰的参数，
而Get请求则只能接收@RequestParam修饰的参数。

如果前端传的是Json类型的对象，后台就要使用@RequestBody修饰的实体类接收，
如果是单个属性就使用@RequestParam修饰的变量或实体类接收

##springboot注解@NotNull，@NotBlank，@Valid自动判定空值
http://www.360doc.com/content/18/0829/14/10072361_782131739.shtml

##Maven中的SNAPSHOT版本和正式版本
https://xiaohuang.blog.csdn.net/article/details/116168412?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_default&utm_relevant_index=1

##springboot常用Starter介绍
https://www.cnblogs.com/zhushilai/p/13564948.html

https://docs.spring.io/spring-boot/docs/2.4.1/reference/html/using-spring-boot.html#using-boot

##使用Hibernate-Validator优雅的验证参数
https://blog.csdn.net/qq_32258777/article/details/86743416

常用注解
注解	释义
@Nul	被注释的元素必须为 null
@NotNull	被注释的元素必须不为 null
@AssertTrue	被注释的元素必须为 true
@AssertFalse	被注释的元素必须为 false
@Min(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max, min)	被注释的元素的大小必须在指定的范围内，元素必须为集合，代表集合个数
@Digits (integer, fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
@Past	被注释的元素必须是一个过去的日期
@Future	被注释的元素必须是一个将来的日期
@Email	被注释的元素必须是电子邮箱地址
@Length(min=, max=)	被注释的字符串的大小必须在指定的范围内，必须为数组或者字符串，若微数组则表示为数组长度，字符串则表示为字符串长度
@NotEmpty	被注释的字符串的必须非空
@Range(min=, max=)	被注释的元素必须在合适的范围内
@NotBlank	被注释的字符串的必须非空
@Pattern(regexp = )	正则表达式校验
@Valid	对象级联校验,即校验对象中对象的属性


## 集成 Swagger2 开发 API 文档（在线+离线）
https://www.zwqh.top/article/info/24

https://zhuanlan.zhihu.com/p/98075551

##OpenApi3/Swagger3简单使用及与swagger2对比
https://blog.csdn.net/long199366/article/details/114388171

java 中 swagger1.x 对应 OpenAPI2、swagger 2.x对应OpenAPI3）

##bug
【BUG】记一次Java模块拆分后报错（invalid bound statement (not found)）
https://blog.csdn.net/weixin_38997187/article/details/100162741


【BUG】@Value注解获取不到配置值
碰到过三种情况导致@Value获取不到配置值

    变量被关键字static修饰
    类没有使用@Component及其衍生标签修饰
    在Bean初始化时构造方法中引用被@Value修饰的变量






