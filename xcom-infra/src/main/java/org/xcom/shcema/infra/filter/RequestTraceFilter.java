package org.xcom.shcema.infra.filter;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.xcom.shcema.core.constant.RequestConstant;
import org.xcom.shcema.core.constant.SystemConstant;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志链路过滤器
 *
 * @author xcom
 * @date 2024/8/9
 */

@Slf4j
@Component
public class RequestTraceFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String traceLogId = request.getHeader(RequestConstant.HEADER_TRACE_LOG_KEY);
        if (StringUtils.isBlank(traceLogId)) {
            traceLogId = UUID.fastUUID().toString();
        }
        MDC.put(SystemConstant.SYSTEM_LOG_TRACE_ID, traceLogId);
        response.addHeader(RequestConstant.HEADER_TRACE_LOG_KEY, traceLogId);

        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            log.info(">>>>>>>>>>>>>>>>请求开始，URL：{}", request.getRequestURL());
            log.info(">>>>>>>>>>>>>>>>请求开始，url：{}", request.getRequestURI());
            filterChain.doFilter(request, response);
            stopWatch.stop();
        } finally {
            log.info(">>>>>>>>>>>>>>>>请求结束，url：{}，耗时：{} ms", request.getRequestURI(), stopWatch.getTotalTimeMillis());
            MDC.clear();
        }

    }

}
