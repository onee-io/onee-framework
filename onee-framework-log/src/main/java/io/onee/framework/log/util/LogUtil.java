package io.onee.framework.log.util;

import io.onee.framework.log.model.vo.AccessLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志打印工具类
 *
 * @author onee
 * @since 1.2.0
 */
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private static String ACCESS_LOG_FORMAT = "";

    static {
        StringBuilder builder = new StringBuilder();
        builder.append("splunk log: ");
        builder.append("clientHost=%s");
        builder.append(", uri=%s");
        builder.append(", costTime=%s");
        builder.append(", requestJson=%s");
        builder.append(", requestSize=%s");
        builder.append(", responseJson=%s");
        builder.append(", responseSize=%s");
        ACCESS_LOG_FORMAT = builder.toString();
    }

    /**
     * 打印 splunk 统计日志
     *
     * @param vo 输出对象
     */
    public static void printSplunkLog(AccessLogVO vo) {
        String message = String.format(ACCESS_LOG_FORMAT,
                vo.getClientHost(),
                vo.getUri(),
                vo.getCostTime(),
                vo.getRequestJson(),
                vo.getRequestSize(),
                vo.getResponseJson(),
                vo.getResponseSize());
        logger.info(SensitiveUtil.messageConvert(message));
    }
}
