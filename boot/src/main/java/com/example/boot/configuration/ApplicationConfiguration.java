package com.example.boot.configuration;

import com.example.application.adapter.in.GetPriceService;
import com.example.domain.port.in.GetPriceUseCase;
import com.example.domain.port.out.GetPricePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GetPriceUseCase getPriceUseCase(final GetPricePort getPricePort) {
        return new GetPriceService(getPricePort);
    }
}
