package com.li.cloud.service;

import com.li.cloud.apis.PayFeignApi;
import com.li.cloud.dto.PayDTO;
import com.li.cloud.resp.ResultData;
import com.li.cloud.vo.PayVO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private PayFeignApi payFeignApi;

    @Override
    public ResultData<Integer> addOrder(PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @Override
    public ResultData<PayVO> getPayInfo(Long payId) {
        return payFeignApi.getById(payId);
    }

    @Override
    public String getInfo() {
        return payFeignApi.getInfo();
    }

    @Override
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitBreakerFallback")
    public String myCircuitBreaker(Long id) {
        return payFeignApi.myCircuit(id);
    }

    public String myCircuitBreakerFallback(Long id, Throwable e) {
        return "myCircuitFallback，系统繁忙，请稍后再试";
    }

    @Override
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadSemaphoreFallback",type = Bulkhead.Type.SEMAPHORE)
    public String myBulkheadSemaphore(Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    public String myBulkheadSemaphoreFallback(Throwable t) {
        return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }

    @Override
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadThreadPoolFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadThreadPool(Integer id) {
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
    }

    @Override
    public String myMicrometer(Integer id) {
        return payFeignApi.myMicrometer(id);
    }

    public CompletableFuture<String> myBulkheadThreadPoolFallback(Integer id, Throwable t) {
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }
}
