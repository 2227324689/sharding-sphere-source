package com.gupao.sharding.example.service.impl;

import com.gupao.sharding.example.dal.model.TOrderInterval;
import com.gupao.sharding.example.dal.mapper.TOrderIntervalMapper;
import com.gupao.sharding.example.service.ITOrderIntervalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mic
 * @since 2021-07-22
 */
@Service
public class TOrderIntervalServiceImpl extends ServiceImpl<TOrderIntervalMapper, TOrderInterval> implements ITOrderIntervalService {

    @Autowired
    TOrderIntervalMapper orderIntervalMapper;

    Random random=new Random();
    @Override
    public void initEnvironment() throws SQLException {
        orderIntervalMapper.createTableIfNotExists();
    }

    @Override
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> orderIds = insertData();
        System.out.println("-------------- Process Success Finish --------------");
    }
    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 100; i++) {
            TOrderInterval order = new TOrderInterval();
            order.setUserId(random.nextInt(10000));
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            order.setCreateTime(randomLocalDateTime(-10,1));
            orderIntervalMapper.insert(order);
            result.add(order.getOrderId());
        }
        return result;
    }

    public static String randomLocalDateTime(int startYear,int endYear){
        int plusMinus = 1;
        if(startYear < 0 && endYear > 0){
            plusMinus = Math.random()>0.5?1:-1;
            if(plusMinus>0){
                startYear = 0;
            }else{
                endYear = Math.abs(startYear);
                startYear = 0;
            }
        }else if(startYear < 0 && endYear < 0){
            plusMinus = -1;

            //两个数交换
            startYear = startYear + endYear;
            endYear  = startYear - endYear;
            startYear = startYear -endYear;

            //取绝对值
            startYear = Math.abs(startYear);
            endYear = Math.abs(endYear);

        }

        LocalDate day = LocalDate.now().plusYears(plusMinus * RandomUtils.nextInt(startYear,endYear));
        int hour = RandomUtils.nextInt(1,24);
        int minute = RandomUtils.nextInt(0,60);
        int second = RandomUtils.nextInt(0,60);
        LocalTime time = LocalTime.of(hour, minute, second);
        DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return DATE_TIME_FORMAT.format(LocalDateTime.of(day, time));
    }

}
