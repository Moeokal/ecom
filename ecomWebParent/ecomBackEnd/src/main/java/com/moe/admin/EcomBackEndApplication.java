package com.moe.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.moe.ecomcommon.entity")
public class EcomBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomBackEndApplication.class, args);
    }

}
