package io.onee.framework.log.convert;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.onee.framework.log.util.SensitiveUtil;

/**
 * 日志脱敏转换器
 * 适用于 logback 日志框架，配置在 conversionRule 中，可替换所有日志中的敏感信息
 * 例：<conversionRule conversionWord="msg" converterClass="io.onee.framework.log.convert.SensitiveConverter" />
 *
 * @author onee
 * @since 1.2.0
 */
public class SensitiveConverter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        // 获取原始日志内容并脱敏
        return SensitiveUtil.messageConvert(event.getFormattedMessage());
    }
}
