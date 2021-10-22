package com.gupao.sharding.example.service;

import com.gupao.sharding.example.dal.model.TOrderStandard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.SQLException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mic
 * @since 2021-07-22
 */
public interface ITOrderStandardService extends IService<TOrderStandard> {
    void initEnvironment() throws SQLException;

    void processSuccess() throws SQLException;
}
