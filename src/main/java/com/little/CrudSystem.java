package com.little;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Hello world!
 *
 */
@MapperScan("com.little.mapper")
@SpringBootApplication
@ServletComponentScan
public class CrudSystem {

    public static void main(String[] args) {
        SpringApplication.run(CrudSystem.class, args);
    }

}
