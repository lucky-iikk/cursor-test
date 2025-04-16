# Spring Boot Hello World Demo

一个简单的Spring Boot项目，包含以下功能：

1. 启动时在控制台打印"hello world"日志
2. 提供一个GET接口返回"hello world"

## 项目结构

- `DemoApplication.java`: 应用程序入口类
- `HelloController.java`: REST接口控制器

## 如何运行

### 使用Maven运行

```bash
mvn spring-boot:run
```

### 编译后运行

```bash
mvn package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## 测试API

启动应用后，访问以下URL:

```
http://localhost:8080/hello
```

将会看到"hello world"响应 