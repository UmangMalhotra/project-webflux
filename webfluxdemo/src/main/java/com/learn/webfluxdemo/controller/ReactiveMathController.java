package com.learn.webfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.webfluxdemo.dto.MyResponse;
import com.learn.webfluxdemo.service.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive-math")
public class ReactiveMathController {

	@Autowired
	private ReactiveMathService mathService;
	
	@GetMapping("reactive-square/{input}")
	public Mono<MyResponse> square(@PathVariable int input) {
		return mathService.square(input);
	}
	
	
	@GetMapping("reactive-table/{input}")
	public Flux<MyResponse> mathTable(@PathVariable int input){
		return mathService.mathTable(input);
	}
	
	@GetMapping(value = "reactive-table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<MyResponse> mathTableStream(@PathVariable int input){
		return mathService.mathTable(input);
	}
	
	@GetMapping("throw/{input}")
	public Mono<MyResponse> squareWithinRange(@PathVariable int input) {
		return Mono.just(input)
					.handle((inputVal, sink) -> {
						if(inputVal > 10 && inputVal < 20) {
							sink.next(inputVal);
						}else {
							sink.error(new RuntimeException("Bad Req"));
						}
					})
					.cast(Integer.class)
					.flatMap(val -> mathService.square(val));
	}
	
	@GetMapping("assignment/{input}")
	public Mono<ResponseEntity<MyResponse>> assignment(@PathVariable int input) {
		return Mono.just(input)
				.filter(i -> i>=10 && i<=20)
				.flatMap(val -> mathService.square(val))
				.map(resp -> ResponseEntity.ok(resp))
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}
}
