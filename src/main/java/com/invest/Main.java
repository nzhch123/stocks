package com.invest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Main.class);
//        springApplication.addListeners(new FeiquListener());
//        SpringApplication.run(FeiQuApplication.class, args);
        springApplication.run(args);
        Thread_timing thr=new Thread_timing();
        thr.start();
    }
}
