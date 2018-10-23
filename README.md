## 第二章、微服务构建
### actuator处识 监控和管理

###Actuator 的 REST 接口

    原生端点是在应用程序里提供众多 Web 接口，通过它们了解应用程序运行时的内部状况。原生端点又可以分成三类：
    
    应用配置类：可以查看应用在运行期的静态信息：例如自动配置信息、加载的springbean信息、yml文件配置信息、环境信息、请求映射信息；
    度量指标类：主要是运行期的动态信息，例如堆栈、请求连、一些健康指标、metrics信息等；
    操作控制类：主要是指shutdown,用户可以发送一个请求将应用的监控功能关闭。
    Actuator 提供了 13 个接口，具体如下表所示。
    
######

    HTTP方法	  路径	        描述
    ###### 应用配置类型
    GET	      /autoconfig	提供了一份自动配置报告，记录哪些自动配置条件通过了，哪些没通过
    GET	      /configprops	描述配置属性(包含默认值)如何注入Bean
    GET	      /beans	    描述应用程序上下文里全部的Bean，以及它们的关系
    GET	      /dump	        获取线程活动的快照
    GET	      /env	        获取全部环境属性
    GET	      /env/{name}	根据名称获取特定的环境属性值
    GET	      /info	        获取应用程序的定制信息，这些信息由info打头的属性提供
    GET	      /mappings	    描述全部的URI路径，以及它们和控制器(包含Actuator端点)的映射关系
    ###### 度量类型
    GET	      /metrics	    报告各种应用程序度量信息，比如内存用量和HTTP请求计数
    GET	      /metrics/{name}	报告指定名称的应用程序度量值
    GET	      /health	    报告应用程序的健康指标，这些值由HealthIndicator的实现类提供
    ###### 操作控制类                                                                                                                                                                                                                                 
    POST	  /shutdown	       关闭应用程序，要求endpoints.shutdown.enabled设置为true
    GET	      /trace	    提供基本的HTTP请求跟踪信息(时间戳、HTTP头等)


#### /health
    {
        "status": "UP",
        "diskSpace": {
            "status": "UP",
            "total": 499963170816,
            "free": 141106266112,
            "threshold": 10485760
        }
    }


##### 注：
    Springboot应用添加actuator后health返回结果不完整解决方法
    management.security.enabled=false
    endpoints.health.sensitive=false
    

### 应用类
#### /autoconfig

 
 
 
 
 
## 第三章、服务治理：spring Cloud Eureka
###服务治理
    服务治理的主要作用：主要用来实现各个微服务实例的自动化注册与发现。

    Eureka 服务端：夜车个服务注册中心。
    Eureka 客户端：主要处理服务的注册和发现。
    
### 高可用注册中心
    Eureka Server的设计一开始就考虑了高可用，在Eureka的服务治理中，所有节点即是服务提供方，也是服务消费房，
    服务注册中心也不例外。是否还记得在但节点的配置中，我们设置过下面这两个参数，让服务注册中心不注册自己
    # 由于该用用为注册中心，so设置false不向注册中心注册自己
    eureka.client.register-with-eureka=false
    # 注册中心的职责是维护服务实例，并不检索服务，so设置false
    eureka.client.fetch-registry=false
    
    高可用原理：Eureka Server 的高可用实际上就是将自己作为服务向其他服务注册中心注册自己，这样
              就可以形成一组互相注册的服务注册中心，已实现服务清淡的互相同步，达到高可用的效果。
               
        
### 服务发现与消费
     服务消费主要完成两个目标：
           一、发现服务
                服务的发现任务有Eureka的客户端完成。
           二、消费服务
                服务消费的任务由Ribbon完成。Ribbon是基于HTTP和TC的客户端负载均衡，他可以通过客户端配置的ribbonServerList服务
                服务端列表去轮询访问以达到均衡负载的作用。
          


###### 注意
       当注册中心挂掉后，消费者服务是可以正常使用的，但是服务端和消费端可以检测到注册中心报错。
       
### 基础架构 
####服务注册中心
####服务提供者
####服务消费者


### 服务治理机制 
##### 服务提供者
###### 服务同步
        服务同步是指：两个服务提供者分别注册到了不同的服务注册中心上，即他们的信息分别被两个服务注册中心所
        维护，由于服务注册中心之间互为服务，当服务提供者发送注册请求到一个服务注册中心时，他会将该请求转发给集群中相连的服务
        信息就可以通过两台服务中心的任意一台获取到了。
        
###### 服务续约
    为了防止注册中心将服务提供者从注册中心踢出

    # 服务续约
    ## 定义服务续约任务的调用的间隔时间-- 心跳检测时间
    eureka.instance.lease-renewal-interval-in-seconds=30
    ## 服务失效的时间
    eureka.instance.lease-expiration-duration-in-seconds=90      
        
##### 服务消费者
###### 获取服务   
    获取服务
    注册服务 默认值true -- 只有注册中心才会设置false
    eureka.client.fetch-registry=true
    获取服务清单的更新时间 /秒
    eureka.client.registry-fetch-interval-seconds=30
           
###### 服务调用
    服务消费者在获取服务清单后，通过服务名可以获得具体的提供服务的实例名和该实例的元数据消息。
    
###### 服务下线
       当服务实例进行正常的关闭操作时，他会触发 一个服务下线的REST请求给Eureka Server，告诉服务注册中心
       服务端在接受请求之后，将该服务状态置为下线（DOWN），并把该下线时间传播出去。
       
#####服务注册中心
######失效踢除
       服务实例非正常下线（内存溢出、网络故障），而服务注册中心并未收到"服务下线"的请求。Eureka Server在启动的时候
       会创建一个定时任务，默认定时一段时间（60s）当前清单中超时（默认90s）没有续约的服务剔除出去。
        # 服务续约
        ## 定义服务续约任务的调用的间隔时间-- 心跳检测时间
        eureka.instance.lease-renewal-interval-in-seconds=30
        ## 服务失效的时间
        eureka.instance.lease-expiration-duration-in-seconds=90    
    
######自我保护
        
        # 注册中心保护机制 true:开启（默认）保护机制  false：关闭保护机制
        eureka.server.enable-self-preservation=true
                
        
        
 ### 服务注册类配置
     服务注册配置信息都以eureka.client为前缀.
     
 ###  服务实例类配置
      服务实例类配置信息都是以eureka.instance为前缀。
      
 ####元数据
 ##### 定义
           它是Eureka客户端在向服务端在向服务注册中心发送助恶请求时，用来描述资深信息的对象，
           其中包括了一些标准化的元数据，比如服务名称、实例名称、实例IP、实例端口等用于服务治
           理的重要信息；以及一些用于负载均衡策略或者其他特殊用途的自定义元数据信息。
           
  ###### 自定义元数据
        通过eureka.instance.<properties>=<value>的格式对标准化元数据直接进行配置，其中
        <properties>就是EurekaInstanceConfigBean对象中的成员变量名。
        自定义元数据
        eureka.instance.metadataMap<key>=<value>的格式来进行配置，
        eureka.instance.matadataMap.zone=Beijing
      
      
 ##### 实例名配置
 
  ##### 端点配置
 
 
 
 
 
## 第四章、客户端负载均衡 Spring cloud Ribbon
       1、Spring cloud Ribbon 是一个基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现。
       2、它几乎存在于每一个Spring Cloud构建的微服务服务和基础设施中。因为微服务间的调用，API网关的请
          求转发等内容，实际上都是通过Ribbon来实现的，包括后续我们将要介绍的Feign，它也是基于Ribbon实
          际的工具。
          
### 客户端负载均衡
    解决的问题：对系统的高可用、网络压力的缓解和处理能力扩容的重要手段之一。
    分类 硬件：主要通过在服务器节点之间安装专门用于负载均衡的设备 比如：F5
        软件：主要通过在服务器上安装一些具有均衡负载功能或模块的软件来完成请求分发工作，比如Nginx
        
    客户端负载均衡和服务端负载均衡的区别
        区别：客户端负载均衡和服务端负载均衡的区别在于服务清单所存储的位置。
        客户端负载均衡中，所有客户端节点都维护着自己的要访问的服务端清单，而这些服务端的清单来自于服务
           注册中心，譬如上一张我们介绍的Eureka服务端。
     
 #### 微服务使用客户端负载均衡调用步骤：
        1、服务提供者只需要启动多个服务实例并注册到一个注册中心或多个相关联的服务注册中心。
        2、服务消费者直接通过调用@LoadBalanced注解修饰过的RestTemplate来实现面向服务的接口调用。
                
              
       
       
### RestTemplate详解
    RestTemplate对象会使用Ribbon的自动化配置，同是通过配置@LoadBalanced才能够开启客户端负载均衡。
    
#### RestTemplate几种不同请求类型和参数类型的服务调用实现
   
#####GET请求
 ######  方式一：getForEntity函数
 ######  方式二：getForObject函数
        
#####POST请求
 ######  方式一：postForEntity函数
                postForEntity(String url,Object request,Class responseType) 
                1、responseType参数是对请求响应的body内容的类型定义。
                2、request参数 
                        a、参数是普通对象，RestTemplate会将请求对象转换为一个HttpEntity对象来处理，其中Object就是request的类型，
                          request内容会被视作完整的body来处理；
                        b、参数是HttpEntity对象，那么就会被当作一个完整的HTTP请求对象来处理，这个request中不仅包含了body的内容，
                           也包含了header的内容。
 ######  方式二：postForObject函数  
 
 ######  方式二：postForLocation函数  
                由于postForLocation函数会返回新资源的URI，该URI就相当于指定了返回类型，so此方法实现的post请求不需要像postEntity和
                postForObject那样指定responseType。
    
           
 #####put请求 
     put函数，put函数为void类型，故put通函数中没有responseType参数。
     
  #####delete请求 
        delete函数 ，通常都将delete请求的唯一标识凭借在url中，所以  delete请求也不需要request的body信息。
        
        
####重试机制
    # 开启重试机制，默认是关闭
    spring.cloud.loadbalancer.retry.enabled=true
    # 断路器的超时时间需要大于Ribbon的超时时间，不然不会出发重试。
    hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=10000
    
    # 请求连接的超时时间
    hello-server-admin.ribbon.ConnectTimeout=250
    # 请求处理的超时时间
    hello-server-admin.ribbon.ReadTimeout=1000
    # 对所有操作请求都进行重试
    hello-server-admin.ribbon.OkToRetryOnAllOperation=true
    # 切换实例的重试次数
    hello-server-admin.ribbon.MaxAutoRetriesNextServer=2
    #对当前实例的重试的次数
    hello-server-admin.ribbon.MaxAutoRetries=1     
        
   
        
        
        
### 第5章、服务容错保护 Spring Cloud Hystrix
        
        
### 第6章、生命是服务调用 Spring Cloud Feign
    1、Spring Cloud Feign在RestTemple的基础上做了进一步封装，由它来帮助我们定义和实现以来服务接口的定义。
    2、在Spring Cloud Feign的实现下，我们只需要创建一个接口并用注解的方式来配置它，即可完成对服务提供放的绑定，
        简化了在Spring Cloud Ribbon时自行封装服务调用客户端的开发量。
    3、Spring Cloud Feign具备可茶吧的注解支持，包括F诶个 i 呢注解和JAX—RS注解，同事，为了适应Spring
       的光大用户，它在Netflix Feign的基础上扩展了对Spring MVC 的注解主持         
        
#### 快速入门        
     1、通过@FeignClient注解指定服务名来绑定服务，然后在使用SpringMVC的注解绑定具体该服务提供的REST接口
     2、服务名部分大小写，所以使用hello-service和HELLO-SERVICE都是可以的。
     
#### 参数绑定
     
       在定义个参数绑定时 @RequestParam，@RequestHeader等可以指定参数名称的注解，
       他们的value千万不能少，在SpringMVC程序中，这些注解会根据参数名来作为默认值，但是在Feign中
       绑定参数必须通过value属性赖智明具体的参数名，不然会跑出IllegalStateException异常，
       value属性不能为空。
        
### 继承特性
    这个没有弄明白，后续内容在研究

            
        
                
### 第7章、API网关服务 Spring Cloud Zuul


#### 快速入门

#### 构建网关      
    Spring Cloud Zuul 中特别提供了/routes端点来返回当前的所有路由规则
    

#### 请求路由
    # 实现传统路由的转发功能
    zuul.routes.api-a-url.path=/api-a-url/**
    zuul.routes.ai-a-url.url=http://localhost:8080/
    
    zuul.routes.api-a-url.path中的api-a-url部分为路由的名字，可以任意定义，
    但是一组path和url映射关系的路由名要相同
    
  
##### 面向服务的路由
    1、传统的路由配置方式对于我们来说并不友好，它同样需要运维人员花费大量的时间来维护各个路由path
    与url的关系。
    
    2、为了解决传统中的关系映射问题实现了实现了面向服务的路由。
    
    
####请求过滤
    为了我在API网关中实现对客户端请求的校验使用请求过滤。
    1、他作为系统的统一入口，屏蔽了系统内部的各个微服务的细节。
    2、他可以与服务治理框架结合，实现自动化的服务实例维护以及负载均衡的路由转发。
    3、它可以实现接口权限校验与微服务业务逻辑的解耦。
    4、通过服务网关中的过滤器，在个生命周期中去校验请求的内容，将原本在对外服务层做的校验前移，保证了
       微服务的无状态行，同事降低了微服务的测试难度，让服务本身更集中关注业务逻辑的处理。
       

#### 路由详解
#####  传统路由
###### 单实例配置
###### 多实例配置

#####  服务路由配置
        对于服务路由，通过与eureka的整合，实现了对服务实例的
        自动化维护，所以在使用服务路由配置的时候，我们不需要向传统胚子
        方式那样为serviceid指定具体的服务实例地址，只需要通过参数方式诶之即可
        

#### 服务路由的默认规则
    spring cloud zuul构建的API网关服务引入Spring Cloud Eureke之后，
    它为Eureka中的每个服务都自动创建一个默认路由规则，这些默认规则的path会
    使用serviceId配置的服务名作为请求前缀。


#### 自定义路由映射规则


#### 路径匹配
    路由匹配算法：1、路由规则匹配请求路径的时候是通过线性遍历的方式，在请求路径获取到第一哥匹配的路由规则之后就
                返回并结束匹配过程。所以当存在多个匹配的路由规则时，匹配结果完全取决于路由跪着的保存顺序。
                2、路由跪着的保存是有序的，而内容的加载是通过遍历配置文件中路由规则依次加入的，所以导致问题的根本原因
                是对配置文件内容的读取。


#### 忽略表达式
   1、 为了更细粒度和更为灵活地配置路由规则，zuul还提供了一个忽略表达式参数zuul.ignored-patterns.
    该参数可以用来设置不希望被API网关进行路由的URL表达式。 
  2、使用范围不是莫哥路由，而是对所有路由，所以在设置的时候需要全面考虑URL规则，防止
  忽略不该忽略的URL路径。  


#### 路由前缀
    zuul.prefix=/lquan
    需要在之前的请求路径中增加一个前缀路径


#### 本地跳转


  
        
        
        
        
        
        
        
        
        
        
        






         