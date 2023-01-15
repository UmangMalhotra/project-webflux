package com.order.webdemo.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.webdemo.productservice.dto.ProductDto;
import com.order.webdemo.productservice.entity.Product;
import com.order.webdemo.productservice.repository.ProductRepository;
import com.order.webdemo.productservice.utility.EntityDtoConverter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Flux<ProductDto> getAll(){
		return this.repository.findAll()
						.map(EntityDtoConverter::toProductDto);
	}
	
	public Mono<ProductDto> getByProductId(String id){
		
		return this.repository.findById(id)
						.map(EntityDtoConverter::toProductDto);
	}
	
	public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono){
		return productDtoMono
					.map(EntityDtoConverter::toProductEntity)
					.flatMap(prod -> this.repository.insert(prod)) //method reference can be used but omitted for readability
					.map(EntityDtoConverter::toProductDto);
	}
	
	public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
		
		return this.repository.findById(id)
						.flatMap(prod -> productDtoMono
											.map(EntityDtoConverter::toProductEntity)
											.doOnNext(product -> product.setId(id)))
						.flatMap(prod -> this.repository.save(prod))
						.map(EntityDtoConverter::toProductDto);
	}
	
	
	public Mono<Void> deleteProductById(String id) {
		return this.repository.deleteById(id);
	}
}
