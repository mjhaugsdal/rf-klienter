package io.github.mjhaugsdal.fhir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ca.uhn.fhir.rest.server.interceptor.LoggingInterceptor;

@SpringBootApplication
public class JerseyRestfulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JerseyRestfulServerApplication.class, args);
    }

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }
}