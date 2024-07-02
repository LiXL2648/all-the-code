package com.li.cloud.service;

import com.li.cloud.dto.PayDTO;
import com.li.cloud.resp.ResultData;
import com.li.cloud.vo.PayVO;

public interface OrderService {

    ResultData<Integer> addOrder(PayDTO payDTO);

    ResultData<PayVO> getPayInfo(Long payId);

    String getInfo();
}
