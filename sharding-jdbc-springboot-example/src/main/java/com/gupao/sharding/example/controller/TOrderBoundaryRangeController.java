package com.gupao.sharding.example.controller;


import com.gupao.sharding.example.service.ITOrderBoundaryRangeService;
import com.gupao.sharding.example.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mic
 * @since 2021-07-22
 * 1000,20000,300000
 * 0~1000
 * 1000~20000
 * 20000~300000
 * 300000~无穷大
 *
 */
@RestController
@RequestMapping("/t-order-boundary-range")
public class TOrderBoundaryRangeController {

    @Autowired
    ITOrderBoundaryRangeService orderService;

    @GetMapping
    public void init() throws SQLException {
        orderService.initEnvironment();
        orderService.processSuccess();
    }
}
