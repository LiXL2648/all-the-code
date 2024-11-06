package com.li.cloud.service;

import com.li.cloud.dto.PayDTO;
import com.li.cloud.resp.ResultData;
import com.li.cloud.vo.PayVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private RestTemplate restTemplate;

    private static final String PAYMENT_SERVICE_URL = "http://cloud-payment-service";

    @Override
    public ResultData<Integer> addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/pay/add", payDTO, ResultData.class);
    }

    @Override
    public ResultData<PayVO> getPayInfo(Long payId) {
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/pay/get/" + payId, ResultData.class, payId);
    }

    @Override
    public String getInfo() {
        return restTemplate.getForObject(PAYMENT_SERVICE_URL + "/pay/get/info", String.class);
    }
}
