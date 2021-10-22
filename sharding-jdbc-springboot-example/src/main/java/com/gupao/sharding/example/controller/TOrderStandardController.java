package com.gupao.sharding.example.controller;


import com.gupao.sharding.example.service.ITOrderStandardService;
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
 */
@RestController
@RequestMapping("/t-order-standard")
public class TOrderStandardController {

    @Autowired
    ITOrderStandardService orderService;

    @GetMapping
    public void init() throws SQLException {
        orderService.initEnvironment();
        orderService.processSuccess();
    }
}
