package com.gupao.sharding.example.service.impl;

import com.gupao.sharding.example.dal.mapper.TOrderIntervalMapper;
import com.gupao.sharding.example.dal.model.TOrderBoundaryRange;
import com.gupao.sharding.example.dal.mapper.TOrderBoundaryRangeMapper;
import com.gupao.sharding.example.dal.model.TOrderInterval;
import com.gupao.sharding.example.service.ITOrderBoundaryRangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mic
 * @since 2021-07-22
 */
@Service
public class TOrderBoundaryRangeServiceImpl extends ServiceImpl<TOrderBoundaryRangeMapper, TOrderBoundaryRange> implements ITOrderBoundaryRangeService {
    @Autowired
    TOrderBoundaryRangeMapper orderBoundaryRangeMapper;

    Random random=new Random();
    @Override
    public void initEnvironment() throws SQLException {
        orderBoundaryRangeMapper.createTableIfNotExists();
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
        for (int i = 1; i <= 1000; i++) {
            TOrderBoundaryRange order = new TOrderBoundaryRange();
            order.setUserId(Long.parseLong(random.nextInt(400000)+""));
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            orderBoundaryRangeMapper.insert(order);
            result.add(order.getOrderId());
        }
        return result;
    }
}
