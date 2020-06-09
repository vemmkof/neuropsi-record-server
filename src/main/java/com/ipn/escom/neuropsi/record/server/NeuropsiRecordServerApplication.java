package com.ipn.escom.neuropsi.record.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NeuropsiRecordServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeuropsiRecordServerApplication.class, args);
    }

}
