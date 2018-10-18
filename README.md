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
       Spring cloud Ribbon 是一个基于HTTP和TCP的客户端负载均衡工具，它基于Netflix Ribbon实现。
       
### 客户端负载均衡
              
       
       
           
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        






         