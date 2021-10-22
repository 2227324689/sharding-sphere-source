package com.gupao.sharding.example.service;

import com.gupao.sharding.example.dal.model.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mic
 * @since 2021-07-19
 */
public interface ITOrderService extends IService<TOrder> {

    void initEnvironment() throws SQLException;

    void processSuccess() throws SQLException;

}
