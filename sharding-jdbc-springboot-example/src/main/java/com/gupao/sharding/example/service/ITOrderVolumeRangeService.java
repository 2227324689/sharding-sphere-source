package com.gupao.sharding.example.service;

import com.gupao.sharding.example.dal.model.TOrderVolumeRange;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.SQLException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mic
 * @since 2021-07-20
 */
public interface ITOrderVolumeRangeService extends IService<TOrderVolumeRange> {

    void initEnvironment() throws SQLException;

    void processSuccess() throws SQLException;
}
