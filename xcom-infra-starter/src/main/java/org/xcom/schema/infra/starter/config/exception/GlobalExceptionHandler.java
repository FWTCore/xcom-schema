package org.xcom.schema.infra.starter.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.xcom.schema.core.enums.SystemCodeEnum;
import org.xcom.schema.core.exception.XcomException;
import org.xcom.schema.core.model.XcomResult;

import java.net.BindException;

/**
 * 全局异常获取
 *
 * @author xcom
 * @date 2024/8/9
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = XcomException.class)
    public XcomResult bizExceptionHandler(HttpServletRequest req, XcomException e) {
        log.error(String.format("发生业务异常！原因是：{%s}", e.toString()), e);
        return XcomResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public XcomResult nullPointerExceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return XcomResult.error(SystemCodeEnum.BUSINESS_EXCEPTION);
    }

    /**
     * 缺少参数字段
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public XcomResult missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        String msg = String.format("请求参数【%s】不能为空", e.getParameterName());
        return XcomResult.error(SystemCodeEnum.PARAMETER_ERROR, msg);
    }

    /**
     * 参数转换异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public XcomResult httpMessageNotReadableExceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.error("参数非法！原因是:", e);
        return XcomResult.error(SystemCodeEnum.PARAMETER_ERROR);
    }

    /**
     * 参数有效性校验
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public XcomResult methodArgumentNotValidHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("参数有效性！原因是:", e);
        return XcomResult.error(SystemCodeEnum.PARAMETER_ERROR);
    }

    /**
     * 非法参数
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public XcomResult methodArgumentTypeMismatchExceptionHandler(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
        log.error("参数非法！原因是:", e);
        return XcomResult.error(SystemCodeEnum.PARAMETER_ERROR);
    }

    /**
     * bing参数异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public XcomResult bindExceptionHandler(HttpServletRequest req, BindException e) {
        log.error("参数非法！原因是:", e);
        return XcomResult.error(SystemCodeEnum.PARAMETER_ERROR);
    }


    /**
     * 找不到资源
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public XcomResult noHandlerFoundExceptionHandler(HttpServletRequest req, NoHandlerFoundException e) {
        log.error("找不到资源！原因是:", e);
        return XcomResult.error(SystemCodeEnum.NOT_FOUND);
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public XcomResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return XcomResult.error(SystemCodeEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public XcomResult noHandlerFoundExceptionHandler(HttpServletRequest req, Throwable e) {
        log.error("其他异常:", e);
        return XcomResult.error(SystemCodeEnum.INTERNAL_SERVER_ERROR);
    }


}