package io.onee.framework.common.distributed.sequence;

/**
 * 分布式序列
 *
 * @author onee
 * @since 1.0.0
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
