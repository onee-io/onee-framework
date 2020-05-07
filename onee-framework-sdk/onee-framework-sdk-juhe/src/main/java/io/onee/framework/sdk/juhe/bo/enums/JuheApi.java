package io.onee.framework.sdk.juhe.bo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 聚合数据api
 * Created by onee
 * Date: 2020/5/6 22:10
 */
@Getter
@AllArgsConstructor
public enum JuheApi {
    J_1("http://apis.juhe.cn/ip/ipNew", "IP地址"),
    J_11("http://apis.juhe.cn/mobile/get", "手机号码归属地"),
    J_21("http://web.juhe.cn:8080/finance/stock/hs", "股票数据"),
    J_23("http://web.juhe.cn:8080/finance/exchange/rmbquot", "货币汇率"),
    J_25("http://v.juhe.cn/jingzhi/query.php", "净值数据"),
    J_26("http://web.juhe.cn:8080/fund/suspend/purch", "暂停基金"),
    J_27("http://web.juhe.cn:8080/fund/zcgjj/", "重仓股基金"),
    J_28("http://web.juhe.cn:8080/fund/findata/main", "基金财务数据"),
    J_29("http://web.juhe.cn:8080/finance/gold/shgold", "黄金数据"),
    J_50("http://apis.juhe.cn/goodbook/catalog", "图书电商数据"),
    J_58("http://web.juhe.cn:8080/constellation/getAll", "星座运势"),
    J_63("http://api.juheapi.com/japi/toh", "历史上的今天"),
    J_64("http://v.juhe.cn/dream/category", "周公解梦"),
    J_65("http://v.juhe.cn/laohuangli/d", "老黄历"),
    J_66("http://v.juhe.cn/postcode/query", "邮编查询"),
    J_69("http://japi.juhe.cn/charconvert/change.from", "简/繁/火星字体转换"),
    J_73("http://apis.juhe.cn/simpleWeather/query", "天气预报"),
    J_80("http://op.juhe.cn/onebox/exchange/query", "汇率"),
    J_95("http://v.juhe.cn/joke/content/list.php", "笑话大全"),
    J_156("http://v.juhe.cn/xhzd/query", "新华字典"),
    J_157("http://v.juhe.cn/chengyu/query", "成语词典"),
    J_166("http://japi.juhe.cn/qqevaluate/qq", "QQ号码测吉凶"),
    J_177("http://v.juhe.cn/calendar/day", "万年历"),
    J_183("http://v.juhe.cn/jztk/query", "驾照题库"),
    J_211("http://v.juhe.cn/telecode/to_telecodes.php", "标准电码查询"),
    J_235("http://v.juhe.cn/toutiao/index", "新闻头条"),
    J_296("http://apis.juhe.cn/qrcode/api", "二维码生成"),
    J_300("http://apis.juhe.cn/lottery/types", "彩票开奖结果查询"),
    J_537("http://apis.juhe.cn/xzqh/query", "全国行政区划查询"),
    J_538("http://apis.juhe.cn/cxdq/brand", "车辆品牌车型大全"),
    J_539("http://apis.juhe.cn/sxpd/query", "生肖配对"),
    J_540("http://apis.juhe.cn/gnyj/query", "今日国内油价查询"),
    J_541("http://apis.juhe.cn/tyfy/query", "中文同义词/反义词查询"),
    J_542("http://apis.juhe.cn/obdcode/query", "汽车OBD故障码查询"),
    J_543("http://apis.juhe.cn/xzpd/query", "星座配对");

    /**
     * 接口地址
     */
    private final String url;

    /**
     * 接口描述
     */
    private final String desc;
}
