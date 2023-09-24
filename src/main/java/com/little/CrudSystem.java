package com.little;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author chengchong
 */
@MapperScan(basePackages = "com.little.mapper")
@SpringBootApplication
@ServletComponentScan
public class CrudSystem {

    public static void main(String[] args) {
        SpringApplication.run(CrudSystem.class, args);
    }

    @Test
    public void testPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("admin123"));
        System.out.println(passwordEncoder.encode("LU666"));
        System.out.println(passwordEncoder.encode("testGuest"));
        System.out.println(passwordEncoder.matches("admin123", "$2a$10$07KLKWZk7XWy9w8A81Hv.OmCnvJ7CIczGwO8.dXhNIRw8aSZcSbF2"));
    }
}
