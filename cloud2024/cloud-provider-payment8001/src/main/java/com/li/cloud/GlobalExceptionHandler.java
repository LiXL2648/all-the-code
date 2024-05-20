package com.li.cloud;

import com.li.cloud.resp.ResultData;
import com.li.cloud.resp.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author LiXL
 * @date 2024/5/20
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResultData<Void> handleRuntimeException(RuntimeException e) {
        log.error("全局异常信息exception:{}", e.getMessage(), e);
        return ResultData.fail(ResultEnum.RC500);
    }
}
