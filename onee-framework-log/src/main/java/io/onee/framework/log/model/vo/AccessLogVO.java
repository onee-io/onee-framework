package io.onee.framework.log.model.vo;

import java.io.Serializable;

/**
 * 请求日志打印参数
 *
 * @author onee
 * @since 1.2.0
 */
public class AccessLogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 请求 host
     */
    private String clientHost;

    /**
     * 接口路径
     */
    private String uri;

    /**
     * 耗时
     */
    private long costTime;

    /**
     * 请求参数 json
     */
    private String requestJson;

    /**
     * 请求参数大小
     */
    private int requestSize;

    /**
     * 响应参数 json
     */
    private String responseJson;

    /**
     * 响应参数大小
     */
    private int responseSize;

    public String getClientHost() {
        return clientHost;
    }

    public void setClientHost(String clientHost) {
        this.clientHost = clientHost;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public int getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(int requestSize) {
        this.requestSize = requestSize;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(int responseSize) {
        this.responseSize = responseSize;
    }
}
