package com.gupaoedu.example.shardingjdbcbasicexample;

import com.gupaoedu.example.shardingjdbcbasicexample.service.ExampleService;
import com.gupaoedu.example.shardingjdbcbasicexample.service.impl.OrderServiceImpl;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ExampleMain {
    public static void main(String[] args) throws SQLException {
        //被Sharding-JDBC代理的datasource
        DataSource dataSource=ShardingDatabaseAndTableConfiguration.getDatasource();
        ExampleService exampleService=new OrderServiceImpl(dataSource);
        exampleService.initEnvironment();
        exampleService.processSuccess();

    }
}
