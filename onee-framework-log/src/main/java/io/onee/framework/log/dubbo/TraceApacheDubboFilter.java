package io.onee.framework.log.dubbo;

import io.onee.framework.log.util.IdUtil;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.protocol.dubbo.DecodeableRpcInvocation;
import org.slf4j.MDC;

/**
 * Dubbo 日志跟踪过滤器
 * 在 consumer和 provider中配置此过滤器，会自动提取 invocation的中的 traceId并传递下去
 * 过滤器会提取请求方的 ip，无需手动处理
 *
 * @author onee
 * @since 1.2.0
 */
public class TraceApacheDubboFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (invocation instanceof DecodeableRpcInvocation) {
            // 被调用方 从 invocation 中获取 traceId 并写入到 MDC 中
            String traceId = invocation.getAttachment("traceId");
            if (traceId == null || traceId.length() == 0) {
                traceId = IdUtil.getUUID();
            }
            MDC.put("traceId", traceId);
            // 获取请求方 IP 并写入到 MDC 中
            String clientIp = RpcContext.getContext().getRemoteAddress().getHostString();
            MDC.put("clientIp", clientIp);
            MDC.put("uri", invocation.getAttachment("uri") == null ? "" : invocation.getAttachment("uri"));
        } else if (invocation instanceof RpcInvocation) {
            RpcInvocation rpcInvocation = (RpcInvocation) invocation;
            // 请求方 从 MDC 中获取 traceId 并放入 invocation 中传递下去
            String traceId = MDC.get("traceId");
            if (traceId == null || traceId.length() == 0) {
                traceId = IdUtil.getUUID();
            }
            rpcInvocation.setAttachment("traceId", traceId);
            rpcInvocation.setAttachment("uri", MDC.get("uri") == null ? "" : MDC.get("uri"));
        }
        return invoker.invoke(invocation);
    }
}
