package io.onee.framework.common.distributed.sequence;

/**
 * 分布式序列
 * Created by onee
 * Date: 2020/4/26 16:49
 */
public interface DistributedSequence {

    /**
     * 获取下一个序列值
     */
    String getNextSequence();

    /**
     * 获取当前序列值
     */
    String getCurrentSequence();
}
