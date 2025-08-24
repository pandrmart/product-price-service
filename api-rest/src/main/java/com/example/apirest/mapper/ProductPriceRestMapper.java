package com.example.apirest.mapper;

import com.example.api.dto.ProductPriceResponse;
import com.example.domain.entity.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductPriceRestMapper {

    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "price.currency", target = "currency")
    ProductPriceResponse toDto(ProductPrice productPrice);
}
