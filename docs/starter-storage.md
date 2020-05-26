# onee-framework-starter-storage

在 spring boot 项目中快速集成云存储服务组件

## 云服务商

目前支持的云服务商如下：
 
- [七牛云](https://www.qiniu.com)

## 准备工作

### 七牛云

1. 注册七牛云账号: [https://portal.qiniu.com/signup](https://portal.qiniu.com/signup)
2. 获取 accessKey 和 secretKey ，路径：`管理控制台` - 右上角 - `密钥管理`
2. 创建存储空间（bucket），路径：`管理控制台` - `对象存储` - `空间管理` - `新建空间`

## 安装

仓库地址：[onee-framework-starter-storage](https://search.maven.org/artifact/io.onee/onee-framework-starter-storage)

### Maven

在 `pom.xml` 中添加以下依赖：

```xml
<dependency>
    <groupId>io.onee</groupId>
    <artifactId>onee-framework-starter-storage</artifactId>
    <version>1.1.0-SNAPSHOT</version>
</dependency>
```

## 参数配置

在 `application.properties` 文件中可配置以下参数：

| 参数名 | 必须 | 默认值 | 说明 |
| ---- | ---- | ---- | ---- |
| of.storage.qiniu.access-key | 是 | - | 七牛云`密钥管理`中的 accessKey |
| of.storage.qiniu.secret-key | 是 | - | 七牛云`密钥管理`中的 secretKey |
| of.storage.qiniu.bucket | 是 | - | 七牛云创建的空间名称 |

### 配置示例

```properties
of.storage.qiniu.access-key=asdasdasdasdasdasdasdasdasdasdasdasdasd
of.storage.qiniu.secret-key=zxczxczxczxczxczxczxczxczxczxczxczxczxc
of.storage.qiniu.bucket=onee
```

## 使用方式

代码示例：[StorageSample](../onee-framework-sample/src/main/java/io/onee/framework/sample/StorageSample.java)

```java
@Autowired
private StorageClient qiniuStorageClient; // 七牛云存储客户端
```
