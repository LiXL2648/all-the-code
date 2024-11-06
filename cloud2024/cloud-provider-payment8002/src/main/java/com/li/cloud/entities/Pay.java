package com.li.cloud.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 表名：t_pay
 * 表注释：支付交易表
*/
@Data
@Table(name = "t_pay")
@Schema(title = "支付交易表entity")
public class Pay {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 支付流水号
     */
    @Column(name = "pay_no")
    private String payNo;

    /**
     * 订单流水号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 用户账号ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 删除标志，默认0不删除，1删除
     */
    private Byte deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}