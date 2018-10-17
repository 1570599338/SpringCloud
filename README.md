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
    
#### 高可用注册中心
    Eureka Server的设计一开始就考虑了高可用，在Eureka的服务治理中，所有节点即是服务提供方，也是服务消费房，
    服务注册中心也不例外。是否还记得在但节点的配置中，我们设置过下面这两个参数，让服务注册中心不注册自己
    # 由于该用用为注册中心，so设置false不向注册中心注册自己
    eureka.client.register-with-eureka=false
    # 注册中心的职责是维护服务实例，并不检索服务，so设置false
    eureka.client.fetch-registry=false
    
    高可用原理：Eureka Server 的高可用实际上就是将自己作为服务向其他服务注册中心注册自己，这样
              就可以形成一组互相注册的服务注册中心，已实现服务清淡的互相同步，达到高可用的效果。
               
        
#### 服务发现与消费
     服务消费主要完成两个目标：
           一、发现服务
                服务的发现任务有Eureka的客户端完成。
           二、消费服务
                服务消费的任务由Ribbon完成。Ribbon是基于HTTP和TC的客户端负载均衡，他可以通过客户端配置的ribbonServerList服务
                服务端列表去轮询访问以达到均衡负载的作用。
          



  