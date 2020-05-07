package io.onee.framework.sdk.juhe;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.onee.framework.sdk.juhe.bo.enums.JuheApi;
import io.onee.framework.sdk.juhe.bo.request.Juhe1Req;
import io.onee.framework.sdk.juhe.bo.response.JuheRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 聚合数据API客户端
 * Created by onee
 * Date: 2020/5/6 20:23
 */
@Slf4j
@Data
@AllArgsConstructor
public class JuheClient {

    /**
     * 接口AppKey配置
     */
    private JuheAppKey appKey;

    /**
     * IP地址
     * https://www.juhe.cn/docs/api/id/1
     */
    public JuheRes j1(Juhe1Req req) {
        return request(JuheApi.J_1, appKey.getJ_1(), req);
    }

    /**
     * 手机号码归属地 TODO
     * https://www.juhe.cn/docs/api/id/11
     */
    public JuheRes j11() {
        return request(JuheApi.J_11, appKey.getJ_11(), null);
    }

    /**
     * 股票数据 TODO
     * https://www.juhe.cn/docs/api/id/21
     */
    public JuheRes j21() {
        return request(JuheApi.J_21, appKey.getJ_21(), null);
    }

    /**
     * 货币汇率 TODO
     * https://www.juhe.cn/docs/api/id/23
     */
    public JuheRes j23() {
        return request(JuheApi.J_23, appKey.getJ_23(), null);
    }

    /**
     * 净值数据 TODO
     * https://www.juhe.cn/docs/api/id/25
     */
    public JuheRes j25() {
        return request(JuheApi.J_25, appKey.getJ_25(), null);
    }

    /**
     * 暂停基金 TODO
     * https://www.juhe.cn/docs/api/id/26
     */
    public JuheRes j26() {
        return request(JuheApi.J_26, appKey.getJ_26(), null);
    }

    /**
     * 重仓股基金 TODO
     * https://www.juhe.cn/docs/api/id/27
     */
    public JuheRes j27() {
        return request(JuheApi.J_27, appKey.getJ_27(), null);
    }

    /**
     * 基金财务数据 TODO
     * https://www.juhe.cn/docs/api/id/28
     */
    public JuheRes j28() {
        return request(JuheApi.J_28, appKey.getJ_28(), null);
    }

    /**
     * 黄金数据 TODO
     * https://www.juhe.cn/docs/api/id/29
     */
    public JuheRes j29() {
        return request(JuheApi.J_29, appKey.getJ_29(), null);
    }

    /**
     * 图书电商数据 TODO
     * https://www.juhe.cn/docs/api/id/50
     */
    public JuheRes j50() {
        return request(JuheApi.J_50, appKey.getJ_50(), null);
    }

    /**
     * 星座运势 TODO
     * https://www.juhe.cn/docs/api/id/58
     */
    public JuheRes j58() {
        return request(JuheApi.J_58, appKey.getJ_58(), null);
    }

    /**
     * 历史上的今天 TODO
     * https://www.juhe.cn/docs/api/id/63
     */
    public JuheRes j63() {
        return request(JuheApi.J_63, appKey.getJ_63(), null);
    }

    /**
     * 周公解梦 TODO
     * https://www.juhe.cn/docs/api/id/64
     */
    public JuheRes j64() {
        return request(JuheApi.J_64, appKey.getJ_64(), null);
    }

    /**
     * 老黄历 TODO
     * https://www.juhe.cn/docs/api/id/65
     */
    public JuheRes j65() {
        return request(JuheApi.J_65, appKey.getJ_65(), null);
    }

    /**
     * 邮编查询 TODO
     * https://www.juhe.cn/docs/api/id/66
     */
    public JuheRes j66() {
        return request(JuheApi.J_66, appKey.getJ_66(), null);
    }

    /**
     * 简/繁/火星字体转换 TODO
     * https://www.juhe.cn/docs/api/id/69
     */
    public JuheRes j69() {
        return request(JuheApi.J_69, appKey.getJ_69(), null);
    }

    /**
     * 天气预报 TODO
     * https://www.juhe.cn/docs/api/id/73
     */
    public JuheRes j73() {
        return request(JuheApi.J_73, appKey.getJ_73(), null);
    }

    /**
     * 汇率 TODO
     * https://www.juhe.cn/docs/api/id/80
     */
    public JuheRes j80() {
        return request(JuheApi.J_80, appKey.getJ_80(), null);
    }

    /**
     * 笑话大全 TODO
     * https://www.juhe.cn/docs/api/id/95
     */
    public JuheRes j95() {
        return request(JuheApi.J_95, appKey.getJ_95(), null);
    }

    /**
     * 新华字典 TODO
     * https://www.juhe.cn/docs/api/id/156
     */
    public JuheRes j156() {
        return request(JuheApi.J_156, appKey.getJ_156(), null);
    }

    /**
     * 成语词典 TODO
     * https://www.juhe.cn/docs/api/id/157
     */
    public JuheRes j157() {
        return request(JuheApi.J_157, appKey.getJ_157(), null);
    }

    /**
     * QQ号码测吉凶 TODO
     * https://www.juhe.cn/docs/api/id/166
     */
    public JuheRes j166() {
        return request(JuheApi.J_166, appKey.getJ_166(), null);
    }

    /**
     * 万年历 TODO
     * https://www.juhe.cn/docs/api/id/177
     */
    public JuheRes j177() {
        return request(JuheApi.J_177, appKey.getJ_177(), null);
    }

    /**
     * 驾照题库 TODO
     * https://www.juhe.cn/docs/api/id/183
     */
    public JuheRes j183() {
        return request(JuheApi.J_183, appKey.getJ_183(), null);
    }

    /**
     * 标准电码查询 TODO
     * https://www.juhe.cn/docs/api/id/211
     */
    public JuheRes j211() {
        return request(JuheApi.J_211, appKey.getJ_211(), null);
    }

    /**
     * 新闻头条 TODO
     * https://www.juhe.cn/docs/api/id/235
     */
    public JuheRes j235() {
        return request(JuheApi.J_235, appKey.getJ_235(), null);
    }

    /**
     * 二维码生成 TODO
     * https://www.juhe.cn/docs/api/id/296
     */
    public JuheRes j296() {
        return request(JuheApi.J_296, appKey.getJ_296(), null);
    }

    /**
     * 彩票开奖结果查询 TODO
     * https://www.juhe.cn/docs/api/id/300
     */
    public JuheRes j300() {
        return request(JuheApi.J_300, appKey.getJ_300(), null);
    }

    /**
     * 全国行政区划查询 TODO
     * https://www.juhe.cn/docs/api/id/537
     */
    public JuheRes j537() {
        return request(JuheApi.J_537, appKey.getJ_537(), null);
    }

    /**
     * 车辆品牌车型大全 TODO
     * https://www.juhe.cn/docs/api/id/538
     */
    public JuheRes j538() {
        return request(JuheApi.J_538, appKey.getJ_538(), null);
    }

    /**
     * 生肖配对 TODO
     * https://www.juhe.cn/docs/api/id/539
     */
    public JuheRes j539() {
        return request(JuheApi.J_539, appKey.getJ_539(), null);
    }

    /**
     * 今日国内油价查询 TODO
     * https://www.juhe.cn/docs/api/id/540
     */
    public JuheRes j540() {
        return request(JuheApi.J_540, appKey.getJ_540(), null);
    }

    /**
     * 中文同义词/反义词查询 TODO
     * https://www.juhe.cn/docs/api/id/541
     */
    public JuheRes j541() {
        return request(JuheApi.J_541, appKey.getJ_541(), null);
    }

    /**
     * 汽车OBD故障码查询 TODO
     * https://www.juhe.cn/docs/api/id/542
     */
    public JuheRes j542() {
        return request(JuheApi.J_542, appKey.getJ_542(), null);
    }

    /**
     * 星座配对 TODO
     * https://www.juhe.cn/docs/api/id/543
     */
    public JuheRes j543() {
        return request(JuheApi.J_543, appKey.getJ_543(), null);
    }

    /**
     * 发送请求
     *
     * @param api    接口
     * @param params 请求参数
     * @return 标准化后的返回结果
     */
    private JuheRes request(JuheApi api, String key, Object params) {
        Map<String, Object> queryParam;
        if (params == null) {
            queryParam = new HashMap<>();
        } else {
            queryParam = JSON.parseObject(JSON.toJSONString(params), new TypeReference<Map<String, Object>>() {
            });
        }
        queryParam.put("key", key);
        log.info("juhe request url={}", HttpUtil.urlWithForm(api.getUrl(), queryParam, CharsetUtil.CHARSET_UTF_8, true));
        String result = HttpUtil.get(api.getUrl(), queryParam);
        log.info("juhe request result={}", result);
        return null; // TODO
    }
}
