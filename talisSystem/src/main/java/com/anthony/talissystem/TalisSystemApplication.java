package com.anthony.talissystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TalisSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalisSystemApplication.class, args);
    }

}
