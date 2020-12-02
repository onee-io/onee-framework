package io.onee.framework.autoconfigure.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * redis配置属性类
 *
 * @author onee
 */
@Data
@ConfigurationProperties("of.redis")
public class RedisProperties {

    /**
     * 单机模式配置
     */
    private Single single;

    /**
     * 哨兵模式配置
     */
    private Sentinel sentinel;

    /**
     * 集群模式配置
     */
    private Cluster cluster;

    /**
     * 空闲连接断开时间
     */
    private int idleConnectionTimeout = 10000;

    /**
     * 连接超时时间
     */
    private int connectTimeout = 10000;

    /**
     * 响应超时时间
     */
    private int timeout = 30000;

    /**
     * redis密码
     */
    private String password;

    /**
     * 连接池最大连接数
     */
    private int connectionPoolSize = 64;

    /**
     * 连接池最小空闲连接数
     */
    private int connectionMinimumIdleSize = 24;

    /**
     * 失败重连尝试次数
     */
    private int retryAttempts = 3;

    /**
     * 失败重连间隔
     */
    private int retryInterval = 1500;

    /**
     * 看门狗超时时间
     */
    private long lockWatchdogTimeout = 5000;

    /**
     * 编解码类
     */
    private String codecClassName = "org.redisson.client.codec.StringCodec";

    /**
     * 单机模式配置
     */
    @Data
    public static class Single {

        /**
         * 服务主机ip
         */
        private String host = "localhost";

        /**
         * 服务端口
         */
        private int port = 6379;

        /**
         * 数据库编号
         */
        private int database = 0;
    }

    /**
     * 哨兵模式配置
     */
    @Data
    public static class Sentinel {

        /**
         * 主服务节点地址
         */
        private String master;

        /**
         * 从服务节点地址列表，多个以","隔开
         */
        private List<String> nodes;

        /**
         * 数据库编号
         */
        private int database = 0;
    }

    /**
     * 集群模式配置
     */
    @Data
    public static class Cluster {

        /**
         * 集群服务务节点地址列表，多个以","隔开
         */
        private List<String> nodes;

        /**
         * 最大转发数量
         */
        private Integer maxRedirects;
    }
}
