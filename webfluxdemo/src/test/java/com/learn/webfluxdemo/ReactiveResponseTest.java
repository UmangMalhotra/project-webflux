package com.learn.webfluxdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.learn.webfluxdemo.dto.MyResponse;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ReactiveResponseTest extends BaseTest{

	@Autowired
	private WebClient webClient;
	
	@Test
	public void blockSingleResponseTest() {
		MyResponse myResponse = webClient
								.get()
								.uri("reactive-math/reactive-square/{number}", 5)
								.retrieve()
								.bodyToMono(MyResponse.class)
								.block();
		
		System.out.println("Response using webclient: "+myResponse);
	}
	
	
	@Test
	public void blockingFluxTest() {
		Flux<MyResponse> responseFlux =  webClient.get()
												.uri("reactive-math/reactive-table/{number}", 5)
												.retrieve()
												.bodyToFlux(MyResponse.class);
		
		StepVerifier.create(responseFlux)
			.expectNextCount(10L)
			.verifyComplete();
		
	}
	
	
	
	@Test
	public void streamFluxTest() {
		Flux<MyResponse> responseFlux = webClient.get()
											.uri("reactive-math/reactive-table/{number}/stream", 5)
											.retrieve()
											.bodyToFlux(MyResponse.class)
											.doOnNext(resp -> System.out.println(resp));
		
		StepVerifier.create(responseFlux)
				.expectNextCount(10L)
				.verifyComplete();
	}
}
