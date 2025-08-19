package com.example.apirest.mapper;

import com.example.apirest.dto.PriceResponseDTO;
import com.example.domain.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {

    @Mapping(source = "money.amount", target = "price")
    @Mapping(source = "money.currency", target = "currency")
    PriceResponseDTO toDto(Price price);
}
