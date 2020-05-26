package io.onee.framework.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis 组件示例测试
 *
 * @author onee
 * @since 1.1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class)
public class RedisSampleTest {

    @Autowired
    private RedisSample redisSample;

    @Test
    public void test() {
        redisSample.redisBaseOperate();
        redisSample.redisLockOperate();
    }
}
