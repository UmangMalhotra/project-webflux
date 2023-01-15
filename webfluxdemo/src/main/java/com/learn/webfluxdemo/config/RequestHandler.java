package com.learn.webfluxdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.webfluxdemo.dto.MyResponse;
import com.learn.webfluxdemo.service.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

	@Autowired
	private ReactiveMathService reactiveMathService;
	
	public Mono<ServerResponse> squareFromRouter(ServerRequest request) {
		
		System.out.println("Handling square request from router config..");
		
		int input = Integer.parseInt(request.pathVariable("input"));
		
		Mono<MyResponse> squareResponse = this.reactiveMathService.square(input);
		
		return ServerResponse.ok().body(squareResponse, MyResponse.class);
	}
	
	
	public Mono<ServerResponse> tableFromRouter(ServerRequest request){
		System.out.println("Handling table request from router config..");
		
		int input = Integer.parseInt(request.pathVariable("input"));
		
		Flux<MyResponse> tableResponse = this.reactiveMathService.mathTable(input);
		
		return ServerResponse.ok().body(tableResponse, MyResponse.class);
	}
	
	
	public Mono<ServerResponse> streamTableFromRouter(ServerRequest request){
		System.out.println("Handling table request from router config..");
		
		int input = Integer.parseInt(request.pathVariable("input"));
		
		Flux<MyResponse> tableResponse = this.reactiveMathService.mathTable(input);
		
		return ServerResponse.ok()
					.contentType(MediaType.TEXT_EVENT_STREAM)
					.body(tableResponse, MyResponse.class);
	}
}
