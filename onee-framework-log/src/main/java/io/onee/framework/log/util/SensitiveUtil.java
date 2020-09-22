package io.onee.framework.log.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 敏感信息工具类
 *
 * @author onee
 * @since 1.2.0
 */
public class SensitiveUtil {

    /**
     * 手机号正则表达式
     */
    private static final String PHONE_REGEX = "1[3|4|5|7|8|9][0-9]\\d{8}";

    /**
     * 身份证号正则表达式
     */
    private static final String ID_CARD_REGEX = "([1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3})";

    /**
     * 信息按规则过滤脱敏
     *
     * @param message 原始信息
     * @return 脱敏后的信息
     */
    public static String messageConvert(String message) {
        // 获取原始日志内容
        if (message == null || message.length() == 0) {
            return message;
        }
        // 返回脱敏后的日志
        message = filter(PHONE_REGEX, message, 3, 3);
        message = filter(ID_CARD_REGEX, message, 4, 4);
        return message;
    }

    /**
     * 过滤指定规则的内容并作脱敏
     *
     * @param regex       正则匹配规则
     * @param content     原始信息
     * @param startLength 开始展示长度
     * @param endLength   末尾展示长度
     * @return 脱敏后的信息
     */
    public static String filter(String regex, String content, int startLength, int endLength) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, desensitize(matcher.group(), startLength, endLength));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 脱敏处理 指定起止展示长度 剩余用*号代替
     *
     * @param content     待脱敏的内容
     * @param startLength 开始展示长度
     * @param endLength   末尾展示长度
     * @return 脱敏后的字符串
     */
    public static String desensitize(String content, int startLength, int endLength) {
        StringBuilder sb = new StringBuilder();
        sb.append(content, 0, startLength);
        for (int i = 0; i < content.length() - startLength - endLength; i++) {
            sb.append("*");
        }
        sb.append(content, content.length() - endLength, content.length());
        return sb.toString();
    }
}
