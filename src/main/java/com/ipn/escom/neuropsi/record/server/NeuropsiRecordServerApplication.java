package com.ipn.escom.neuropsi.record.server;

import com.ipn.escom.neuropsi.commons.exception.NoDataException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan("com.ipn.escom.neuropsi.commons")
//@EntityScan("com.ipn.escom.neuropsi.commons.entity")
public class NeuropsiRecordServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeuropsiRecordServerApplication.class, args);
    }

    private void method() throws NoDataException {
    }
}
