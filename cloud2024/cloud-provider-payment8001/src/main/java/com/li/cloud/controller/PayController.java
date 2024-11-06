package com.li.cloud.controller;

import cn.hutool.core.util.IdUtil;
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

import java.util.concurrent.TimeUnit;

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

//    @Value("${li.info}")
    private String info;

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
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }

        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Pay pay = payService.getById(id);
        PayVO payVO = new PayVO();
        BeanUtils.copyProperties(pay, payVO);
        return ResultData.success(payVO);
    }

    @GetMapping("get/info")
    @Operation(summary = "获取consul配置信息")
    public String getInfoByConsul() {
        return info + " port: 8001";
    }

    @GetMapping(value = "circuit/{id}")
    @Operation(summary = "测试熔断降级", description = "测试断路器")
    public String myCircuit(@PathVariable("id") Long id) {
        if (id == -4) {
            throw new RuntimeException("----circuit id 不能负数");
        } else if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    @GetMapping(value = "bulkhead/{id}")
    @Operation(summary = "测试隔离", description = "测试舱壁")
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        if(id == -4) throw new RuntimeException("----bulkhead id 不能-4");

        if(id == 9999)
        {
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        return "Hello, bulkhead! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }

    @GetMapping(value = "micrometer/{id}")
    @Operation(summary = "测试链路监控", description = "进行链路监控")
    public String myMicrometer(@PathVariable("id") Integer id)
    {
        return "Hello, 欢迎到来myMicrometer inputId:  "+id+" \t    服务返回:" + IdUtil.simpleUUID();
    }

    @GetMapping(value = "gateway/get/{id}")
    @Operation(summary = "测试网关1", description = "测试网关1")
    public ResultData<Pay> getById1(@PathVariable("id") Long id)
    {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "gateway/info")
    @Operation(summary = "测试网关2", description = "测试网关2")
    public ResultData<String> getGatewayInfo()
    {
        return ResultData.success("gateway info test："+ IdUtil.simpleUUID());
    }
}
