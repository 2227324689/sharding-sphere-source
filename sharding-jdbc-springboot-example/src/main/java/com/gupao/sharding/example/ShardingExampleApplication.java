package com.gupao.sharding.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gupao.sharding.example.dal.mapper")
public class ShardingExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingExampleApplication.class, args);
    }
}
