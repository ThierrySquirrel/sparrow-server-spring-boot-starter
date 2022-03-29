# sparrow-server-spring-boot-starter

Sparrow Server Spring Book Edition

[中文](./README_zh_CN.md)

Support Function:

- [x] Server Cluster
- [x] [Sparrow Server Dedicated Client](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

# Server Cluster:

The Cluster Is Determined By The Client And Can Connect To A Specified Number Of Servers

# Sparrow Server Dedicated Client

[Sparrow Server Dedicated Client,Usually, All Requests Come From Here](https://github.com/ThierrySquirrel/sparrow-spring-boot-starter)

## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
<dependency>
    <artifactId>sparrow-server-spring-boot-starter</artifactId>
    <groupId>com.github.thierrysquirrel</groupId>
    <version>2.2.1.4-RELEASE</version>
</dependency>
``` 

### configuration file

 ```properties
 ## application.properties
spring.datasource.url=#H2 DataBase Url
spring.datasource.username=#H2 DataBase username
spring.datasource.password=#H2 DataBase password
sparrow.server.url=127.0.0.1:6060 # This Is Required For Service Startup
 ```

# Start Sparrow Server

 ```java

@SpringBootApplication
public class SparrowServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
 ```