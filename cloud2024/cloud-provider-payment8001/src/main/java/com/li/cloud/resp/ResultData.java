package com.li.cloud.resp;

import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author LiXL
 * @date 2024/5/20
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;

    private String message;

    private T data;

    private Long timestamp;

    public ResultData() {
        timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<T>().setCode(ResultEnum.RC200.getCode())
                .setMessage(ResultEnum.RC200.getMessage()).setData(data);
    }

    public static <T> ResultData<T> fail(String code, String message) {
        return new ResultData<T>().setCode(code).setMessage(message);
    }

    public static <T> ResultData<T> fail(ResultEnum resultEnum) {
        return fail(resultEnum.getCode(), resultEnum.getMessage());
    }
}
