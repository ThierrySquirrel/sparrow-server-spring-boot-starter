# sparrow-server-spring-boot-starter

Sparrow Server Spring Book Edition

[中文](./README_zh_CN.md)

Support Function:
- [x] Server Cluster
- [x] [Services Dedicated To sparrow-spring-boot-starter](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

# Server Cluster:  
 Each Server Handles Sparrow Requests On Its Own, And The Cluster Configuration Handles Caching  

# Services Dedicated To Sparrow 
 [Services Dedicated To Sparrow,Usually, All Requests Come From Here](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
        <dependency>
            <artifactId>sparrow-server-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>1.0.2-RELEASE</version>
        </dependency>
``` 

 ### configuration file
 
 ```properties
 ## application.properties
spring.datasource.url= #MySQL DataBase Url
spring.datasource.username= #MySQL DataBase username
spring.datasource.password= #MySQL DataBase password
sparrow.server.url=127.0.0.1:6060 # This Is Required For Service Startup
sparrow.server.cluster-server-url=127.0.0.1:6060,127.0.0.1:6061 # If You Need A Cluster, Please Fill In This Way
 ```

 # Start Sparrow Server
 ```java
 @SpringBootApplication
 public class SparrowServerApplication{
     public static void main(String[] args){
         SpringApplication.run(DemoApplication.class, args);
     }
    
 }
 ```