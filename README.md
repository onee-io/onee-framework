# onee framwork

[![GitHub Release](https://img.shields.io/github/release/onee-io/onee-framework.svg)](https://github.com/onee-io/onee-framework/releases)
[![Maven Central](https://img.shields.io/maven-central/v/io.onee/onee-framework.svg)](https://search.maven.org/search?q=g:%22io.onee%22)
[![License](https://img.shields.io/:license-MulanPSL2-blue.svg)](https://license.coscl.org.cn/MulanPSL2/)
[![JDK](https://img.shields.io/badge/JDK-8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

Spring Boot 项目快速开发框架，用于规范项目开发，提供公共类库、中间件、三方服务 SDK 等组件的快速集成能力，避免重复造轮子，提高效率，保证代码质量。

## 公共类库

- `Result`: web 接口层标准返回格式；
- `ResultCode`: web 接口层标准错误码；
- `IErrorCode`: 错误码结构定义，无论是接口错误码或是异常错误码，均使用此格式定义；
- `BaseException`: 基础异常类，可结合异常错误码一起使用，自定义业务异常类，发生异常时抛出；

## 中间件

- `redis`: 提供单点、哨兵、集群模式初始化客户端，并提供分布式锁操作；

## 快速集成 starter

- [onee-framework-starter-redis](./docs/starter-redis.md)