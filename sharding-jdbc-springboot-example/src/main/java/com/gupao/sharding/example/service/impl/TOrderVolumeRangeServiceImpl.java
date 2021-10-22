package com.gupao.sharding.example.service.impl;

import com.gupao.sharding.example.dal.mapper.TOrderMapper;
import com.gupao.sharding.example.dal.model.TOrder;
import com.gupao.sharding.example.dal.model.TOrderVolumeRange;
import com.gupao.sharding.example.dal.mapper.TOrderVolumeRangeMapper;
import com.gupao.sharding.example.service.ITOrderVolumeRangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mic
 * @since 2021-07-20
 */
@Service
public class TOrderVolumeRangeServiceImpl extends ServiceImpl<TOrderVolumeRangeMapper, TOrderVolumeRange> implements ITOrderVolumeRangeService {

    @Autowired
    TOrderVolumeRangeMapper orderMapper;
    Random random=new Random();
    @Override
    public void initEnvironment() throws SQLException {
        orderMapper.createTableIfNotExists();
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
        for (int i = 0; i < 600; i++) {
            TOrderVolumeRange order = new TOrderVolumeRange();
            order.setUserId(Long.parseLong(i+""));
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            orderMapper.insert(order);
            result.add(order.getOrderId());
        }
        return result;
    }
}
