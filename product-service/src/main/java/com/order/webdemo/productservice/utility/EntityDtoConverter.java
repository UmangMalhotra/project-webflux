package com.order.webdemo.productservice.utility;

import com.order.webdemo.productservice.dto.ProductDto;
import com.order.webdemo.productservice.entity.Product;

public class EntityDtoConverter {

	public static ProductDto toProductDto(Product product) {
		//using MapStruct is more efficient than using BeanUtils.copyProperties() OR ModelMapper library
		//using MapStruct is less time consuming than manually calling setter and getters for mapping beans
		
		return ProductMapper.INSTANCE.toDto(product); 
	}
	
	public static Product toProductEntity(ProductDto productDto) {
		return ProductMapper.INSTANCE.toEntity(productDto);
	}
}
