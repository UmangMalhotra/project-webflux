package com.order.webdemo.productservice.utility;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.order.webdemo.productservice.dto.ProductDto;
import com.order.webdemo.productservice.entity.Product;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	//@Mapping(source = "entiryOfTypeA", target = "dtoOfTypeA")
	ProductDto toDto(Product product);
	
	Product toEntity(ProductDto productDto);
}
