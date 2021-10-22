package com.gupao.sharding.example.controller;


import com.gupao.sharding.example.service.ITOrderVolumeRangeService;
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
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/t-order-volume-range")
public class TOrderVolumeRangeController {
    @Autowired
    ITOrderVolumeRangeService volumeRangeService;
    @GetMapping
    public void init() throws SQLException {
        volumeRangeService.initEnvironment();
        volumeRangeService.processSuccess();
    }
}
