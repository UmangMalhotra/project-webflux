package com.learn.webfluxdemo.config;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.webfluxdemo.dto.MyResponse;
import com.learn.webfluxdemo.exception.MyIllegalOperation;
import com.learn.webfluxdemo.service.CalculateOperationService;

import reactor.core.publisher.Mono;

@Service
public class CalculateRequestHandler {

	@Autowired
	private CalculateOperationService opService;
	
	public Mono<ServerResponse> sumHandler(ServerRequest request){
		System.out.println("Inside sumHandler");
		return process(request, (a,b) -> ServerResponse.ok().bodyValue(a+b));
	}
	
	public Mono<ServerResponse> subtractHandler(ServerRequest request){
		System.out.println("Inside subtractHandler");
		return process(request, (a,b) -> ServerResponse.ok().bodyValue(a-b));
	}
	
	public Mono<ServerResponse> multiplyHandler(ServerRequest request){
		System.out.println("Inside multiplyHandler");
		return process(request, (a,b) -> ServerResponse.ok().bodyValue(a*b));
	}
	
	public Mono<ServerResponse> divideHandler(ServerRequest request){
		System.out.println("Inside divideHandler");
		return process(request, (a,b) -> {
			if(b == 0) {
				return Mono.error(() -> new MyIllegalOperation("Do you want infity ? Illegal OP"));
			}
			Mono<MyResponse> divideMono = opService.divide(a,b);
			return ServerResponse.ok().body(divideMono, MyResponse.class);
		});
	}
	
	public Mono<ServerResponse> process(ServerRequest request, BiFunction<Integer, Integer, Mono<ServerResponse>> opLogic) {
		
		int a = Integer.parseInt(request.pathVariable("op1"));
		int b = Integer.parseInt(request.pathVariable("op2"));
		
		return opLogic.apply(a, b);
	}
	
	/*
	public Mono<ServerResponse> subtractHandler(ServerRequest request){
		int a = Integer.parseInt(request.pathVariable("op1"));
		int b = Integer.parseInt(request.pathVariable("op2"));
		System.out.println("Inside subtractHandler");
		Mono<MyResponse> subtractMono = opService.subtract(a,b);
		return ServerResponse.ok().body(subtractMono, MyResponse.class);
	}
	
	public Mono<ServerResponse> multiplyHandler(ServerRequest request) {
		
		int a = Integer.parseInt(request.pathVariable("op1"));
		int b = Integer.parseInt(request.pathVariable("op2"));
		System.out.println("Inside multiplyHandler");
		Mono<MyResponse> multiplyMono = opService.multiply(a, b);
		return ServerResponse.ok().body(multiplyMono, MyResponse.class);
	}
	
	public Mono<ServerResponse> divideHandler(ServerRequest request){
		
		int a = Integer.parseInt(request.pathVariable("op1"));
		int b = Integer.parseInt(request.pathVariable("op2"));
		System.out.println("Inside divideHandler");
		if(b == 0) {
			return Mono.error(() -> new MyIllegalOperation("Do you want infity ? Illegal OP"));
		}
		
		Mono<MyResponse> divideMono = opService.divide(a,b);
		return ServerResponse.ok().body(divideMono, MyResponse.class);
	} */
	
	
}
