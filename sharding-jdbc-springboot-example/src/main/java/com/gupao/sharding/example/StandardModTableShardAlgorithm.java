package com.gupao.sharding.example;

import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.*;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class StandardModTableShardAlgorithm implements StandardShardingAlgorithm<Long> {

    private Properties props=new Properties();

    /**
     * 用于处理=和IN的分片。
     * @param collection 表示目标分片的集合
     * @param preciseShardingValue 逻辑表相关信息
     * @return
     * Mod
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for(String name:collection){
            //根据order_id的值进行取模，得到一个目标值
            //Order_id%4=3
            //name.endsWith,  "order_3".endWith("")
            if(name.endsWith(String.valueOf(preciseShardingValue.getValue()%4))){
                return name;
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * 用于处理BETWEEN AND分片，如果不配置RangeShardingAlgorithm，SQL中的BETWEEN AND将按照全库路由处理
     * @param collection
     * @param rangeShardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        Collection<String> result=new LinkedHashSet<>(collection.size());
        for(Long i=rangeShardingValue.getValueRange().lowerEndpoint();i<=rangeShardingValue.getValueRange().upperEndpoint();i++){
            for(String name:collection){
                if(name.endsWith(String.valueOf(i%4))){
                    result.add(name);
                }
            }
        }
        return result;
    }

    /**
     * 初始化对象的时候调用的方法
     */
    @Override
    public void init() {

    }

    /**
     * 对应分片算法（sharding-algorithms）的类型
     * @return
     */
    @Override
    public String getType() {
        return "STANDARD_MOD";
    }


    @Override
    public Properties getProps() {
        return this.props;
    }

    /**
     * 获取分片相关属性
     * @param properties
     */
    @Override
    public void setProps(Properties properties) {
        this.props=properties;
    }
}
