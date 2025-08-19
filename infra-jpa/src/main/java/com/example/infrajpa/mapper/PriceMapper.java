package com.example.infrajpa.mapper;

import com.example.domain.entity.Price;
import com.example.infrajpa.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "money", expression = "java(new Money(entity.getPrice(), entity.getCurrency()))")
    Price toDomain(PriceEntity entity);
}
