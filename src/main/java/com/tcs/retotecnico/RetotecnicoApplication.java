package com.tcs.retotecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tcs")
public class RetotecnicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetotecnicoApplication.class, args);
    }
}
