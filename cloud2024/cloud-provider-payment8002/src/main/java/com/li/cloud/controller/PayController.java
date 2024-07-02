package com.li.cloud.controller;

import com.li.cloud.dto.PayDTO;
import com.li.cloud.entities.Pay;
import com.li.cloud.resp.ResultData;
import com.li.cloud.service.PayService;
import com.li.cloud.vo.PayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author LiXL
 * @date 2024/5/20
 */
@RestController
@RequestMapping("pay")
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "add")
    @Operation(summary = "新增", description = "新增支付流水")
    public ResultData<Integer> addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        return ResultData.success(i);
    }

    @DeleteMapping(value = "del/{id}")
    @Operation(summary = "删除", description = "删除支付流水")
    public ResultData<Integer> deletePay(@PathVariable("id") Long id) {
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping(value = "update")
    @Operation(summary = "修改", description = "修改支付流水")
    public ResultData<Integer> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return ResultData.success(i);
    }

    @GetMapping(value = "get/{id}")
    @Operation(summary = "查询", description = "查询支付流水")
    public ResultData<PayVO> getById(@PathVariable("id") Long id) {
        Pay pay = payService.getById(id);
        PayVO payVO = new PayVO();
        BeanUtils.copyProperties(pay, payVO);
        return ResultData.success(payVO);
    }

    @Value("${li.info}")
    private String info;

    @GetMapping("get/info")
    @Operation(summary = "获取consul配置信息")
    public String getInfoByConsul() {
        return info + " port: 8002";
    }
}
