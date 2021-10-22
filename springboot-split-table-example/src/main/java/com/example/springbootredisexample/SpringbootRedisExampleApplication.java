package com.example.springbootredisexample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootredisexample.dal.mapper")
public class SpringbootRedisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisExampleApplication.class, args);
    }

}
