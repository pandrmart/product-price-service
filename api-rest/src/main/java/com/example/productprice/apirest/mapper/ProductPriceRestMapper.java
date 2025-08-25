package com.example.productprice.apirest.mapper;

import com.example.api.dto.ProductPriceResponse;
import com.example.productprice.domain.entity.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductPriceRestMapper {

    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "price.currency", target = "currency")
    ProductPriceResponse toDto(ProductPrice productPrice);
}
