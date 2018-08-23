package com.pnuema.java.barcode.barcodeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

@SpringBootApplication
@EnableCaching
public class BarcodeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarcodeApiApplication.class, args);
    }

    @Bean
    public Filter filter(){
        return new ShallowEtagHeaderFilter();
    }
}
