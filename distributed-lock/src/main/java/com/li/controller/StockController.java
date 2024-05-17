package com.li.controller;

import com.li.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LiXL
 * @date 2024/3/16
 */
@RestController
public class StockController {

    @Resource
    private StockService stockService;

    @GetMapping("check/lock")
    public String checkAndLock() {
        stockService.checkAndLock();
        return "success";
    }
}
