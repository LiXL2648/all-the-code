package com.li.cloud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author LiXL
 * @date 2024/5/20
 */
@Data
@Schema(title = "支付交易DTO")
public class PayDTO {

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
}
