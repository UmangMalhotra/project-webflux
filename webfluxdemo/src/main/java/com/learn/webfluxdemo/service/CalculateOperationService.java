package com.learn.webfluxdemo.service;

import org.springframework.stereotype.Service;

import com.learn.webfluxdemo.dto.MyResponse;

import reactor.core.publisher.Mono;

@Service
public class CalculateOperationService {

	public Mono<MyResponse> sum(int a, int b){
		return Mono.fromSupplier(() -> {
			int result = a + b;
			return new MyResponse(result);
		});
	}
	
	public Mono<MyResponse> subtract(int a, int b){
		return Mono.fromSupplier(() -> {
			int result = a - b;
			return new MyResponse(result);
		});
	}
	
	public Mono<MyResponse> multiply(int a, int b){
		return Mono.fromSupplier(() -> {
			int result = a * b;
			return new MyResponse(result);
		});
	}
	
	public Mono<MyResponse> divide(int a, int b){
		return Mono.fromSupplier(() -> {
			int result = a / b;
			return new MyResponse(result);
		});
	}
}
