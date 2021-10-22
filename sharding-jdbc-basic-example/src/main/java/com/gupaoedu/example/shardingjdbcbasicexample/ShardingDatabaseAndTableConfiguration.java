package com.gupaoedu.example.shardingjdbcbasicexample;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 * //所有的开源项目，一定会有一个example的模块。
 **/
public class ShardingDatabaseAndTableConfiguration {

    private static Map<String, DataSource> createDataSourceMap(){
        //代表真实的数据源
        Map<String,DataSource> dataSourceMap=new HashMap<>();
        dataSourceMap.put("ds0",DataSourceUtil.createDataSource("shard01"));
        dataSourceMap.put("ds1",DataSourceUtil.createDataSource("shard02"));
        return dataSourceMap;
    }
    //创建分片规则
    // * 针对数据库
    // * 针对表
       //* 一定要配置分片键
       //* 一定要配置分片算法
       //* 完全唯一id的问题
    private static ShardingRuleConfiguration createShardingRuleConfiguration(){
        ShardingRuleConfiguration configuration=new ShardingRuleConfiguration();
        //把逻辑表和真实表的对应关系添加到分片规则配置中
        configuration.getTables().add(getOrderTableRuleConfiguration());
        //设置数据库分库规则
        configuration.setDefaultDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration
                        ("user_id","db-inline"));
        Properties properties=new Properties();
        properties.setProperty("algorithm-expression","ds${user_id%2}");
        //设置分库策略
        configuration.getShardingAlgorithms().
                put("db-inline",new ShardingSphereAlgorithmConfiguration("INLINE",properties));

        //设置表的分片规则（数据的水平拆分）
        configuration.setDefaultTableShardingStrategy(new StandardShardingStrategyConfiguration
                ("order_id","order-inline"));
        //设置分表策略
        Properties props=new Properties();
        props.setProperty("algorithm-expression","t_order_${order_id%2}");
        configuration.getShardingAlgorithms().put("order-inline",
                new ShardingSphereAlgorithmConfiguration("INLINE",props));
        //设置主键生成策略
        // * UUID
        // * 雪花算法
        Properties idProperties=new Properties();
        idProperties.setProperty("worker-id","123");
        configuration.getKeyGenerators().put("snowflake",new ShardingSphereAlgorithmConfiguration(
                "SNOWFLAKE",idProperties));
        return configuration;
    }
    //配置逻辑表以及表的id策略
    private static ShardingTableRuleConfiguration getOrderTableRuleConfiguration(){
        ShardingTableRuleConfiguration tableRuleConfiguration=
                new ShardingTableRuleConfiguration("t_order","ds${0..1}.t_order_${0..1}");
        tableRuleConfiguration.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id","snowflake"));
        return tableRuleConfiguration;
    }

    public static DataSource getDatasource() throws SQLException {
        return ShardingSphereDataSourceFactory
                .createDataSource(createDataSourceMap(), Collections.singleton(createShardingRuleConfiguration()),new Properties());
    }
}
