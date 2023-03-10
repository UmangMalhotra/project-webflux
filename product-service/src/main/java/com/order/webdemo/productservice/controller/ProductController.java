package com.order.webdemo.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.webdemo.productservice.dto.ProductDto;
import com.order.webdemo.productservice.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("all")
	public Flux<ProductDto> getAll(){
		return this.productService.getAll();
	}
	
	@GetMapping("{id}")
	public  Mono<ResponseEntity<ProductDto>> getByProductId(@PathVariable String id){
		return this.productService.getByProductId(id)
					.map(pDto -> ResponseEntity.ok(pDto))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDtoMono) {
		return this.productService.insertProduct(productDtoMono);
	}
	
	@PutMapping("{id}")
	public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDtoMono) {
		return this.productService.updateProduct(id, productDtoMono)
					.map(pDto -> ResponseEntity.ok(pDto))
					.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public Mono<Void> deleteProductById(@PathVariable String id){
		return this.productService.deleteProductById(id);
	}
}
