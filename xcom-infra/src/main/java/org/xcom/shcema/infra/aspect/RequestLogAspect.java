package org.xcom.shcema.infra.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xcom.shcema.core.constant.SymbolConstant;
import org.xcom.shcema.core.tool.JsonUtil;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 请求日志 切面
 *
 * @author xcom
 * @date 2024/8/10
 */

@Slf4j
@Component
@Aspect
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RequestLogAspect {

    /**
     * 切点表达式，匹配所有Controller类中的方法
     */
    private final String controllerPointcut = "execution(* *..*Controller.*(..))";

    /**
     * 定义切点
     */
    @Pointcut(controllerPointcut)
    public void controllerExecution() {}

    /**
     * 环绕通知，可以包围Controller方法的执行，提供更灵活的逻辑处理
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerExecution()")
    public Object printRequestLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Method targetMethod = methodSignature.getMethod();
        String methodName = targetMethod.getDeclaringClass().getName() + "." + targetMethod.getName();
        Object[] args = proceedingJoinPoint.getArgs();
        String[] parameterNames = methodSignature.getParameterNames();
        StringBuilder argsBuilder = new StringBuilder();
        if (args != null && args.length > 0) {
            try {
                for (int i = 0, len = args.length; i < len; i++) {
                    Object arg = args[i];
                    if (arg instanceof Serializable) {
                        arg = JsonUtil.toJsonString(arg);
                    }
                    argsBuilder.append(parameterNames[i]).append(SymbolConstant.COLON).append(arg);
                    if (i < len - 1) {
                        argsBuilder.append(SymbolConstant.COMMA);
                    }
                }
            } catch (Exception exception) {
                log.error("【controller】请求参数处理失败，不影响业务，方法名称：{}，异常：{}", methodName, exception);
            }
        }
        log.info("【controller】方法名称：{}，请求参数：{}", methodName, argsBuilder);
        return proceedingJoinPoint.proceed();

    }


}
