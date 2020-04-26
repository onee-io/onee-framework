package io.onee.framework.common.distributed.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式重入锁
 * Created by onee
 * Date: 2020/4/26 16:11
 */
public interface DistributedReentrantLock {

    /**
     * 获取锁
     */
    void lock();

    /**
     * 获取锁
     *
     * @param leaseTime 锁持有时间（到期释放锁）
     * @param unit      时间单位
     */
    void lock(long leaseTime, TimeUnit unit);

    /**
     * 尝试获取锁
     *
     * @param waitTime 等待时间
     * @param unit     时间单位
     * @return 等待时间内拿到锁返回 true，否则 false
     * @throws InterruptedException
     */
    boolean tryLock(long waitTime, TimeUnit unit) throws InterruptedException;

    /**
     * 尝试获取锁
     *
     * @param waitTime  等待时间
     * @param leaseTime 锁持有时间（到期释放锁）
     * @param unit      时间单位
     * @return 等待时间内拿到锁返回 true，否则 false
     * @throws InterruptedException
     */
    boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException;

    /**
     * 解锁
     */
    void unlock();

    /**
     * 当前是否持有锁
     *
     * @return 锁住返回 true，否则 false
     */
    boolean isLocked();

    /**
     * 当前线程是否持有锁
     *
     * @return 锁住返回 true，否则 false
     */
    boolean isLockedByCurrentThread();
}
