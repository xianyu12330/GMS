package com.example.gms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.gms.dao")
@SpringBootApplication
public class GmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmsApplication.class, args);
    }

}
