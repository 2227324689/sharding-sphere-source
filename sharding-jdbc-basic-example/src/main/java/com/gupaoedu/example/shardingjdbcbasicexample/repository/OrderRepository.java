package com.gupaoedu.example.shardingjdbcbasicexample.repository;

import com.gupaoedu.example.shardingjdbcbasicexample.entity.Order;

import java.sql.SQLException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public interface OrderRepository {

    void createTableIfNotExists() throws SQLException;

    Long insert(final Order order) throws SQLException;
}
