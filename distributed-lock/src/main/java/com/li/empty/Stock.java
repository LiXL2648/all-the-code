package com.li.empty;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiXL
 * @date 2024/3/16
 */
@TableName("db_stock")
@Data
public class Stock {

    @TableId
    private Long id;

    private String productCode;

    private String stockCode;

    private Integer count;

    private Integer version;
}
