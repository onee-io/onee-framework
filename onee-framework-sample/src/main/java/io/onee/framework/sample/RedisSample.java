package io.onee.framework.sample;

import io.onee.framework.common.distributed.lock.DistributedReentrantLock;
import io.onee.framework.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis 组件使用示例
 *
 * @author onee
 * @since 1.1.0
 **/
@Slf4j
@Service
public class RedisSample {

    @Autowired
    private RedisClient redisClient;

    /**
     * redis 基本操作示例
     */
    public void redisBaseOperate() {
        // tips: 请先修改 application.properties 中 of.redis.* 相关配置
        String key = "onee:test:1";
        String value = "this is a test value";
        log.info("{}={}", key, redisClient.get(key));
        redisClient.set(key, value);
        log.info("{}={}", key, redisClient.get(key));
        redisClient.delete(key);
        log.info("{}={}", key, redisClient.get(key));
    }

    /**
     * redis 加锁操作示例
     */
    public void redisLockOperate() {
        // tips: 请先修改 application.properties 中 of.redis.* 相关配置
        String lockKey = "onee:test:2";
        DistributedReentrantLock lock = redisClient.getLock(lockKey);
        try {
            if (!lock.tryLock(3, TimeUnit.SECONDS)) {
                log.error("超过等待时间未拿到锁");
                return;
            }
            log.info("do something...");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            lock.unlock();
        }
    }
}
