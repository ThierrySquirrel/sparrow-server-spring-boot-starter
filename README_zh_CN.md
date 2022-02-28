# sparrow-server-spring-boot-starter

Sparrow Server Spring Book Edition

[English](./README.md)

支持功能:

- [x] 服务器集群
- [x] [Sparrow Server专用客户端](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

# 服务器集群:

集群由客户端决定,可以连接指定数量的服务器

# Sparrow Server专用客户端

[Sparrow专用客户端,通常,所有请求都来自这里](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
<dependency>
    <artifactId>sparrow-server-spring-boot-starter</artifactId>
    <groupId>com.github.thierrysquirrel</groupId>
    <version>2.2.1.3-RELEASE</version>
</dependency>
``` 

### 配置文件

 ```properties
 ## application.properties
spring.datasource.url=#H2数据库Url
spring.datasource.username=#H2数据库username
spring.datasource.password=#H2数据库password
sparrow.server.url=127.0.0.1:6060 # 这是必须填写的，用于服务启动
 ```

# 启动Sparrow Server

 ```java

@SpringBootApplication
public class SparrowServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
 ```