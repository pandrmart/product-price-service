package com.example.apirest.dto;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public record PriceRequestDTO(
        @NonNull LocalDateTime applicationDate,
        @NonNull Long productId,
        @NonNull Long brandId) {}