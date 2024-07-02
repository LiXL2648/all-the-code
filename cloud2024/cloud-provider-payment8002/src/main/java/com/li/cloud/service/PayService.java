package com.li.cloud.service;

import com.li.cloud.entities.Pay;

import java.util.List;

/**
 * @author LiXL
 * @date 2024/5/20
 */
public interface PayService {

    int add(Pay pay);

    int delete(Long id);

    int update(Pay pay);

    Pay getById(Long id);

    List<Pay> getAll();
}
