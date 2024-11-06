package com.li.cloud.service;

import com.li.cloud.dto.PayDTO;
import com.li.cloud.resp.ResultData;
import com.li.cloud.vo.PayVO;

import java.util.concurrent.CompletableFuture;

public interface OrderService {

    ResultData<Integer> addOrder(PayDTO payDTO);

    ResultData<PayVO> getPayInfo(Long payId);

    String getInfo();

    String myCircuitBreaker(Long id);

    String myBulkheadSemaphore(Integer id);

    CompletableFuture<String> myBulkheadThreadPool(Integer id);

    String myMicrometer(Integer id);
}
