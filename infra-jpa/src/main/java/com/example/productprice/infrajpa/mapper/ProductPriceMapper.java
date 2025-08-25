package com.example.productprice.infrajpa.mapper;

import com.example.productprice.domain.entity.ProductPrice;
import com.example.productprice.infrajpa.entity.ProductPriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {

    @Mapping(target = "price", expression = "java(new Price(entity.getPrice(), entity.getCurrency()))")
    ProductPrice toDomain(ProductPriceEntity entity);
}
