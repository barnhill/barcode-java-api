package com.pnuema.java.barcode.barcodeapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@OpenAPIDefinition(
        servers = {
                @Server(url = "https://barcode.pnuema.com/", description = "Remote"),
                @Server(url = "http://localhost:8080/", description = "Local")
        }
)
@SpringBootApplication(scanBasePackages = "com.pnuema.java.barcode.barcodeapi.controllers")
@EntityScan(basePackages = "com.pnuema.java.barcode.barcodeapi.controllers")
@EnableCaching
public class BarcodeApiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BarcodeApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BarcodeApiApplication.class);
    }

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
        filter.setWriteWeakETag(true);
        return filter;
    }
}
