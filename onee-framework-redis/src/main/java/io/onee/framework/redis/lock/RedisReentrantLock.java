package io.onee.framework.redis.lock;

import io.onee.framework.common.distributed.lock.DistributedReentrantLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁
 * Created by onee
 * Date: 2020/4/26 16:26
 */
public class RedisReentrantLock implements DistributedReentrantLock {

    private RLock lock;

    public RedisReentrantLock(RedissonClient redissonClient, String lockKey) {
        this.lock = redissonClient.getLock("lock:" + lockKey);
    }

    public void lock() {
        try {
            this.lock.lock();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void lock(long leaseTime, TimeUnit unit) {
        try {
            this.lock.lock(leaseTime, unit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean tryLock(long waitTime, TimeUnit unit) throws InterruptedException {
        try {
            return this.lock.tryLock(waitTime, unit);
        } catch (InterruptedException ie) {
            throw ie;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        try {
            return this.lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException ie) {
            throw ie;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void unlock() {
        try {
            if (this.lock.isHeldByCurrentThread()) {
                this.lock.unlock();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLocked() {
        return this.lock.isLocked();
    }

    public boolean isLockedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }
}
