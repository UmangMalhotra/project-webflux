package com.learn.webfluxdemo.service;

import java.time.Duration;
import org.springframework.stereotype.Service;
import com.learn.webfluxdemo.dto.MyResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

	public Mono<MyResponse> square(int input) {
		return Mono.fromSupplier(() -> new MyResponse(input * input));
	}
	
	public Flux<MyResponse> mathTable(int input){
		 return Flux.range(1, 10)
				 	.delayElements(Duration.ofSeconds(1L))
				 	.doOnNext(val -> System.out.println("Reactive Stream processing number: "+ val))
				 	.map(val -> new MyResponse(val * input));
	}
}
