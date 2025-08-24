package com.example.infrajpa.mapper;

import com.example.domain.entity.ProductPrice;
import com.example.infrajpa.entity.ProductPriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {

    @Mapping(target = "price", expression = "java(new Price(entity.getPrice(), entity.getCurrency()))")
    ProductPrice toDomain(ProductPriceEntity entity);
}
