package com.gupaoedu.example.shardingjdbcbasicexample.service;

import java.sql.SQLException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public interface ExampleService {
    /**
     * 初始化表结构
     *
     * @throws SQLException SQL exception
     */
    void initEnvironment() throws SQLException;

    /**
     * 执行成功
     *
     * @throws SQLException SQL exception
     */
    void processSuccess() throws SQLException;
}
