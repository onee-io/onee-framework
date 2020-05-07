package io.onee.framework.autoconfigure.juhe;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 聚合数据属性配置类
 * Created by onee
 * Date: 2020/5/6 23:36
 */
@Data
@ConfigurationProperties("of.juhe.key")
public class JuheProperties {

    /**
     * IP地址
     */
    private String j_1;

    /**
     * 手机号码归属地
     */
    private String j_11;

    /**
     * 股票数据
     */
    private String j_21;

    /**
     * 货币汇率
     */
    private String j_23;

    /**
     * 净值数据
     */
    private String j_25;

    /**
     * 暂停基金
     */
    private String j_26;

    /**
     * 重仓股基金
     */
    private String j_27;

    /**
     * 基金财务数据
     */
    private String j_28;

    /**
     * 黄金数据
     */
    private String j_29;

    /**
     * 图书电商数据
     */
    private String j_50;

    /**
     * 星座运势
     */
    private String j_58;

    /**
     * 历史上的今天
     */
    private String j_63;

    /**
     * 周公解梦
     */
    private String j_64;

    /**
     * 老黄历
     */
    private String j_65;

    /**
     * 邮编查询
     */
    private String j_66;

    /**
     * 简/繁/火星字体转换
     */
    private String j_69;

    /**
     * 天气预报
     */
    private String j_73;

    /**
     * 汇率
     */
    private String j_80;

    /**
     * 笑话大全
     */
    private String j_95;

    /**
     * 新华字典
     */
    private String j_156;

    /**
     * 成语词典
     */
    private String j_157;

    /**
     * QQ号码测吉凶
     */
    private String j_166;

    /**
     * 万年历
     */
    private String j_177;

    /**
     * 驾照题库
     */
    private String j_183;

    /**
     * 标准电码查询
     */
    private String j_211;

    /**
     * 新闻头条
     */
    private String j_235;

    /**
     * 二维码生成
     */
    private String j_296;

    /**
     * 彩票开奖结果查询
     */
    private String j_300;

    /**
     * 全国行政区划查询
     */
    private String j_537;

    /**
     * 车辆品牌车型大全
     */
    private String j_538;

    /**
     * 生肖配对
     */
    private String j_539;

    /**
     * 今日国内油价查询
     */
    private String j_540;

    /**
     * 中文同义词/反义词查询
     */
    private String j_541;

    /**
     * 汽车OBD故障码查询
     */
    private String j_542;

    /**
     * 星座配对
     */
    private String j_543;
}
