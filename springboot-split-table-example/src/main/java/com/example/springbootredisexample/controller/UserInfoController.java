package com.example.springbootredisexample.controller;


import com.example.springbootredisexample.MybatisPlusConfig;
import com.example.springbootredisexample.dal.model.UserInfo;
import com.example.springbootredisexample.service.IUserInfoService;
import com.example.springbootredisexample.utils.ConsistentHashing;
import com.example.springbootredisexample.utils.SnowFlakeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-07-15
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    IUserInfoService userInfoService;
    SnowFlakeGenerator snowFlakeGenerator=new SnowFlakeGenerator(1,1,1);
    @PostMapping("/batch")
    public void user(@RequestBody List<UserInfo> userInfos){
        log.info("begin UserInfoController.user");
        userInfoService.saveBatch(userInfos);
    }

    @PostMapping
    public void signal(@RequestBody UserInfo userInfo){
        Long bizId=snowFlakeGenerator.nextId();
        userInfo.setBizId(bizId);
        String table=ConsistentHashing.getServer(bizId.toString());
        log.info("UserInfoController.signal:{}",table);
        MybatisPlusConfig.TABLE_NAME.set(table);
        userInfoService.save(userInfo);
    }
}
