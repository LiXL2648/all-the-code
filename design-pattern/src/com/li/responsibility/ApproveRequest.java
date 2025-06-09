package com.li.responsibility;

import java.math.BigDecimal;

public class ApproveRequest {

    // 请求类型
    private final String type;
    // 请求ID
    private final String id;
    // 价格
    private final BigDecimal price;

    public ApproveRequest(String type, String id, BigDecimal price) {
        this.type = type;
        this.id = id;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
