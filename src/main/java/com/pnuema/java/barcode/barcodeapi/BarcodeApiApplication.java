package com.pnuema.java.barcode.barcodeapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@OpenAPIDefinition(servers = {
        @Server(url = "https://barcode.pnuema.com/", description = "Remote"),
        @Server(url = "https://barcodeapi.link/", description = "Remote"),
        @Server(url = "http://localhost:8443/", description = "Local")
})
@SpringBootApplication
@EnableCaching
public class BarcodeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarcodeApiApplication.class, args);
    }

    @Bean
    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
        ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
        filter.setWriteWeakETag(true);
        return filter;
    }
}
