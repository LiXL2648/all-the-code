package com.li.cloud.controller;

import com.li.cloud.dto.PayDTO;
import com.li.cloud.resp.ResultData;
import com.li.cloud.service.OrderService;
import com.li.cloud.vo.PayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
@Tag(name = "订单微服务模块", description = "支付CRUD")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增", description = "新增支付流水")
    public ResultData<Integer> addOrder(@RequestBody PayDTO payDTO) {
        return orderService.addOrder(payDTO);
    }

    @GetMapping("/pay/get/{payId}")
    @Operation(summary = "查询", description = "查询支付流水")
    public ResultData<PayVO> getPayInfo(@PathVariable("payId") Long payId) {
        return orderService.getPayInfo(payId);
    }

    @GetMapping(value = "/pay/get/info")
    @Operation(summary = "获取consul配置信息", description = "获取consul配置信息")
    private String getInfo()
    {
        return orderService.getInfo();
    }
}
