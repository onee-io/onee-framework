package io.onee.framework.autoconfigure.redis;

import io.onee.framework.redis.RedisClient;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * redis 工具客户端自动装配类
 * Created by onee
 * Date: 2020/4/26 19:23
 */
@Configuration
@ConditionalOnClass({RedisClient.class, Redisson.class, RedisOperations.class})
@AutoConfigureBefore({RedisAutoConfiguration.class})
@EnableConfigurationProperties(RedisProperties.class)
public class RedisClientAutoConfiguration {

    @Autowired
    private RedisProperties properties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean({RedissonClient.class})
    public RedissonClient redissonClient() {
        // 构建redisson连接配置
        Config config = new Config();
        config.setCodec(new StringCodec());
        if (properties.getSentinel() != null) {
            // 哨兵模式配置
            RedisProperties.Sentinel sentinel = properties.getSentinel();
            config.useSentinelServers()
                    .setMasterName(sentinel.getMaster())
                    .addSentinelAddress(convert(sentinel.getNodes()))
                    .setDatabase(sentinel.getDatabase())
                    .setMasterConnectionPoolSize(properties.getConnectionPoolSize())
                    .setSlaveConnectionPoolSize(properties.getConnectionPoolSize())
                    .setMasterConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize())
                    .setSlaveConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize())
                    .setRetryAttempts(properties.getRetryAttempts())
                    .setRetryInterval(properties.getRetryInterval())
                    .setConnectTimeout(properties.getConnectTimeout())
                    .setTimeout(properties.getTimeout())
                    .setIdleConnectionTimeout(properties.getIdleConnectionTimeout())
                    .setPassword(properties.getPassword());
        } else if (properties.getCluster() != null) {
            // 集群模式配置
            RedisProperties.Cluster cluster = properties.getCluster();
            config.useClusterServers()
                    .addNodeAddress(convert(cluster.getNodes()))
                    .setMasterConnectionPoolSize(properties.getConnectionPoolSize())
                    .setSlaveConnectionPoolSize(properties.getConnectionPoolSize())
                    .setMasterConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize())
                    .setSlaveConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize())
                    .setRetryAttempts(properties.getRetryAttempts())
                    .setRetryInterval(properties.getRetryInterval())
                    .setConnectTimeout(properties.getConnectTimeout())
                    .setTimeout(properties.getTimeout())
                    .setIdleConnectionTimeout(properties.getIdleConnectionTimeout())
                    .setPassword(properties.getPassword());
        } else if (properties.getSingle() != null) {
            // 单机模式配置
            RedisProperties.Single single = properties.getSingle();
            config.useSingleServer()
                    .setAddress("redis://" + single.getHost() + ":" + single.getPort())
                    .setDatabase(single.getDatabase())
                    .setConnectionPoolSize(properties.getConnectionPoolSize())
                    .setConnectionMinimumIdleSize(properties.getConnectionMinimumIdleSize())
                    .setRetryAttempts(properties.getRetryAttempts())
                    .setRetryInterval(properties.getRetryInterval())
                    .setConnectTimeout(properties.getConnectTimeout())
                    .setTimeout(properties.getTimeout())
                    .setIdleConnectionTimeout(properties.getIdleConnectionTimeout())
                    .setPassword(properties.getPassword());
        }
        // 创建redisson客户端
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnMissingBean({RedisConnectionFactory.class})
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redissonClient) {
        return new RedissonConnectionFactory(redissonClient);
    }

    @Bean
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean({RedisClient.class})
    public RedisClient redisUtil(RedissonClient redissonClient, StringRedisTemplate stringRedisTemplate) {
        return new RedisClient(redissonClient, stringRedisTemplate);
    }

    /**
     * 节点地址格式化
     *
     * @param addresses 地址列表
     * @return 格式化后的地址列表
     */
    private String[] convert(List<String> addresses) {
        List<String> nodes = new ArrayList<>();
        addresses.forEach(address -> {
            if (!address.startsWith("redis://") && !address.startsWith("rediss://")) {
                nodes.add("redis://" + address);
            } else {
                nodes.add(address);
            }
        });
        return nodes.toArray(new String[0]);
    }
}
