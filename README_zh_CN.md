# sparrow-server-spring-boot-starter

Sparrow Server Spring Book Edition

[English](./README.md)

支持功能:
- [x] 服务器集群
- [x] [Sparrow专用服务器](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

# 服务器集群:  
 每个服务器单独处理sparrow请求,集群配置用于处理缓存  

# Sparrow专用服务器  
 [Sparrow专用服务器,通常,所有请求都来自这里](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
        <dependency>
            <artifactId>sparrow-server-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>2.0.0-RELEASE</version>
        </dependency>
``` 

 ### 配置文件
 
 ```properties
 ## application.properties
spring.datasource.url= #MySQL数据库Url
spring.datasource.username= #MySQL数据库username
spring.datasource.password= #MySQL数据库password
mybatis.configuration.map-underscore-to-camel-case=true # 开启Mybatis转换
sparrow.server.url=127.0.0.1:6060 # 这是必须填写的，用于服务启动
sparrow.server.cluster-server-url=127.0.0.1:6060,127.0.0.1:6061 # 如果您需要集群,请这样填写
 ```

 # 启动Sparrow Server
 ```java
 @SpringBootApplication
 public class SparrowServerApplication{
     public static void main(String[] args){
         SpringApplication.run(DemoApplication.class, args);
     }
    
 }
 ```