package com.example.boot.configuration;

import com.example.application.adapter.in.GetProductPriceService;
import com.example.domain.port.in.GetProductPriceUseCase;
import com.example.domain.port.out.GetProductPricePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GetProductPriceUseCase getProductPriceUseCase(final GetProductPricePort getProductPricePort) {
        return new GetProductPriceService(getProductPricePort);
    }
}
