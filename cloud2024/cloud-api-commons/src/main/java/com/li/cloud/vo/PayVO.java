package com.li.cloud.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Schema(title = "支付交易VO")
public class PayVO {

    @Schema(title = "支付流水ID")
    private Long id;

    //支付流水号
    @Schema(title = "支付流水号")
    private String payNo;

    //订单流水号
    @Schema(title = "订单流水号")
    private String orderNo;

    //用户账号ID
    @Schema(title = "用户账号ID")
    private Long userId;

    //交易金额
    @Schema(title = "交易金额")
    private BigDecimal amount;

    @Schema(title = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(title = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
