package com.gupaoedu.example.shardingjdbcbasicexample.service.impl;

import com.gupaoedu.example.shardingjdbcbasicexample.entity.Order;
import com.gupaoedu.example.shardingjdbcbasicexample.repository.OrderRepository;
import com.gupaoedu.example.shardingjdbcbasicexample.repository.impl.OrderRepositoryImpl;
import com.gupaoedu.example.shardingjdbcbasicexample.service.ExampleService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class OrderServiceImpl implements ExampleService {
    private final OrderRepository orderRepository;
    Random random=new Random();

    public OrderServiceImpl(final DataSource dataSource) {
        orderRepository=new OrderRepositoryImpl(dataSource);
    }

    @Override
    public void initEnvironment() throws SQLException {
        orderRepository.createTableIfNotExists();
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
        for (int i = 1; i <= 10; i++) {
            Order order = insertOrder(i);
            result.add(order.getOrderId());
        }
        return result;
    }
    private Order insertOrder(final int i) throws SQLException {
        Order order = new Order();
        order.setUserId(random.nextInt(10000));
        order.setAddressId(i);
        order.setStatus("INSERT_TEST");
        orderRepository.insert(order);
        return order;
    }
}
