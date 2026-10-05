package com.newsaggregator.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication(scanBasePackages = "com.newsaggregator")
@EntityScan("com.newsaggregator.model.entities")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}