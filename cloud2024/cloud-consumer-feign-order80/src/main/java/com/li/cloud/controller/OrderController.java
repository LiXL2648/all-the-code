package com.li.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.li.cloud.dto.PayDTO;
import com.li.cloud.resp.ResultData;
import com.li.cloud.resp.ResultEnum;
import com.li.cloud.service.OrderService;
import com.li.cloud.vo.PayVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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
        try {
            System.out.println("--调用开始：" + DateUtil.now());
            return orderService.getPayInfo(payId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--调用结束：" + DateUtil.now());
            return ResultData.fail(ResultEnum.RC500.getCode(), e.getMessage());
        }
    }

    @GetMapping(value = "/pay/get/info")
    @Operation(summary = "获取consul配置信息", description = "获取consul配置信息")
    private String getInfo()
    {
        return orderService.getInfo();
    }

    @GetMapping(value = "/pay/circuit/{id}")
    @Operation(summary = "测试熔断降级", description = "测试断路器")
    public String myCircuitBreaker(@PathVariable("id") Long id) {
        return orderService.myCircuitBreaker(id);
    }

    @GetMapping(value = "/pay/bulkhead/semaphore/{id}")
    @Operation(summary = "测试隔离", description = "舱壁-信号量")
    public String myBulkheadSemaphore(@PathVariable("id") Integer id)
    {
        return orderService.myBulkheadSemaphore(id);
    }

    @GetMapping(value = "/pay/bulkhead/threadPool/{id}")
    @Operation(summary = "测试隔离", description = "舱壁-固定线程池")
    public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + "\t" + "enter the method!!!");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "exist the method!!!");

        return orderService.myBulkheadThreadPool(id);
    }

    @GetMapping(value = "/pay/micrometer/{id}")
    @Operation(summary = "测试链路监控", description = "进行链路监控")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return orderService.myMicrometer(id);
    }
}
