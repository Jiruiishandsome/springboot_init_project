# Springboot初始化项目模板

```
作者：Jerry
```

基于 SpringBoot 的项目初始模板，整合了常用框架和主流业务的示例代码。

只需 几分钟即可完成后端！大家还可以在此基础上快速开发自己的项目。

## 模板内容

- Spring Boot 2.6.6
- Spring MVC
- MyBatis + MyBatis Plus 
- Spring Boot 调试工具和项目处理器
- Spring Scheduler 定时任务
- Spring 事务注解

### 数据存储

- MySQL 数据库
- Redis 数据库

### 工具类

- Email工具类
- JWT工具类
- MD5工具类
- Lombok 注解
- Redis工具类
- Time工具类

### 业务特性

- 全局请求响应拦截器（记录日志）
- 全局异常处理器
- 自定义错误码
- 封装通用响应类
- Swagger + Knife4j 接口文档
- 自定义权限注解 + 全局校验
- 全局跨域处理

## 业务功能

- 提供示例 SQL（用户表）
- 用户登录、注册，发送验证码
- 支持文件上传
- 支持WebSocket通信

### 单元测试

- JUnit5 单元测试
- 示例单元测试类

### 架构设计

- 合理分层

## 快速上手

将项目克隆到本地后，修改**application.yml**文件中的数据库配置

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ai_teacher?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: root

```

修改**utils/EmailUtil.java**中以下信息，填入自己的

```java
    //邮件服务器主机名
    // QQ邮箱的 SMTP 服务器地址为: smtp.qq.com
    private static String myEmailSMTPHost = "邮件服务器主机名";

    //发件人邮箱
    private static String myEmailAccount = "发件人邮箱";

    //发件人邮箱密码（授权码）
    //在开启SMTP服务时会获取到一个授权码，把授权码填在这里
    private static String myEmailPassword = "授权码";

```

然后启动服务就好了。Swagger + Knife4j 接口文档地址：

http://localhost:8080/doc.html



持续更新中。。。