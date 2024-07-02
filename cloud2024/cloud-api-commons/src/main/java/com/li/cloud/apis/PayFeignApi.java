package com.li.cloud.apis;

import com.li.cloud.dto.PayDTO;
import com.li.cloud.resp.ResultData;
import com.li.cloud.vo.PayVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    @PostMapping(value = "/pay/add")
    ResultData<Integer> addPay(@RequestBody PayDTO payDTO);

    @GetMapping(value = "/pay/get/{id}")
    ResultData<PayVO> getById(@PathVariable("id") Long id);

    @GetMapping("/pay/get/info")
    String getInfo();

    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Long id);

    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);
}
