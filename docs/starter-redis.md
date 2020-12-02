# onee-framework-starter-redis

在 spring boot 项目中快速集成 redis 服务组件

## 模式

目前支持 `单点`、`哨兵`、`集群` 3种模式

## 安装

仓库地址：[onee-framework-starter-redis](https://search.maven.org/artifact/io.onee/onee-framework-starter-redis)

### Maven

在 `pom.xml` 中添加以下依赖：

```xml
<dependency>
    <groupId>io.onee</groupId>
    <artifactId>onee-framework-starter-redis</artifactId>
    <version>1.2.2-SNAPSHOT</version>
</dependency>
```

## 参数配置

在 `application.properties` 文件中可配置以下参数：

| 参数名 | 必须 | 默认值 | 说明 |
| ---- | ---- | ---- | ---- |
| of.redis.single.host | 否 | localhost | 单机模式服务 host，配置则使用单机模式连接 redis 服务（默认） |
| of.redis.single.port | 否 | 6379 | 单机服务端口 |
| of.redis.single.database | 否 | 0 | 单机模式数据库编号 |
| of.redis.sentinel.master | 否 | - | 哨兵模式服务主服务节点地址，配置则使用哨兵模式连接 redis 服务 |
| of.redis.sentinel.nodes | 否 | - | 哨兵模式从服务节点地址列表，多个以","隔开 |
| of.redis.sentinel.database | 否 | 0 | 哨兵模式数据库编号 |
| of.redis.cluster.nodes | 否 | - | 集群模式服务节点地址列表，多个以","隔开 |
| of.redis.cluster.max-redirects | 否 | - | 集群模式最大转发数量 |
| of.redis.idleConnectionTimeout | 否 | 10000 | 空闲连接断开时间 |
| of.redis.connectTimeout | 否 | 10000 | 连接超时时间 |
| of.redis.timeout | 否 | 30000 | 响应超时时间 |
| of.redis.password | 否 | - | redis 密码 |
| of.redis.connectionPoolSize | 否 | 64 | 连接池最大连接数 |
| of.redis.connectionMinimumIdleSize | 否 | 24 | 连接池最小空闲连接数 |
| of.redis.retryAttempts | 否 | 3 | 失败重连尝试次数 |
| of.redis.retryInterval | 否 | 1500 | 失败重连间隔 |
| of.redis.codecClassName | 否 | org.redisson.client.codec.StringCodec | 编解码类 |

### 配置示例

#### 单点

```properties
of.redis.single.host=127.0.0.1
of.redis.single.port=6379
of.redis.password=123456
```

#### 哨兵

```properties
of.redis.sentinel.master=127.0.0.1:6379
of.redis.sentinel.nodes=127.0.0.1:6380,127.0.0.1:6381
of.redis.password=123456
```

#### 集群

```properties
of.redis.cluster.nodes=127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
of.redis.password=123456
```

## 使用方式

代码示例：[StorageSample](../onee-framework-sample/src/main/java/io/onee/framework/sample/RedisSample.java)

```java
@Autowired
private RedisClient redisClient; // redis 工具客户端
```
