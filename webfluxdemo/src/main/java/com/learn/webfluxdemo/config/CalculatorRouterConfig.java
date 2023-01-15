package com.learn.webfluxdemo.config;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.webfluxdemo.exception.MyIllegalOperation;

import reactor.core.publisher.Mono;

@Configuration
public class CalculatorRouterConfig {

	@Autowired
	CalculateRequestHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> mainRouter(){
		System.out.println("Inside Calculator mainRouter config..");
		return RouterFunctions.route()
					.path("calculate/{op1}/{op2}", this::calculate)
					.build();
	}
	
	private RouterFunction<ServerResponse> calculate() {

		System.out.println("Inside Calculate RouterFunction..");
		
		/*
		Predicate<Headers> headersSumPredicate = passedHeaders -> {
			List<String> headerValue = passedHeaders.header("OP");
			System.out.println("SumPredicate_Check for header values:"+headerValue);
			boolean result = "+".equals(headerValue.get(0))? true : false;
			System.out.println("Sum Predicate result: "+ result);
			return result;
		};
		
		Predicate<Headers> headersSubtractPredicate = passedHeaders -> {
			List<String> headerValue = passedHeaders.header("OP");
			System.out.println("SubtractPredicate_Check for header values:"+headerValue);
			return "-".equals(headerValue.get(0))? true : false;
		};
		
		Predicate<Headers> headersMultiplyPredicate = passedHeaders -> {
			List<String> headerValue = passedHeaders.header("OP");
			System.out.println("MultiplyPredicate_Check for header values:"+headerValue);
			return "*".equals(headerValue.get(0))? true : false;
		};
		
		Predicate<Headers> headersDividePredicate = passedHeaders -> {
			List<String> headerValue = passedHeaders.header("OP");
			System.out.println("DividePredicate_Check for header values:"+headerValue);
			return "/".equals(headerValue.get(0))? true : false;
		};
		*/
	
		return RouterFunctions.route()
					.GET(isOperation("+"), handler::sumHandler)
					.GET(isOperation("-"), handler::subtractHandler)
					.GET(isOperation("*"), handler::multiplyHandler)
					.GET(isOperation("/"), handler::divideHandler)
					.GET(request -> ServerResponse.badRequest().bodyValue("OP should be + - * /"))
					.onError(MyIllegalOperation.class, exceptionHandler())
					.build();
	
	}
	
	private RequestPredicate isOperation(String operation) {
		return RequestPredicates
				.headers(passedHeaders -> operation.equals(passedHeaders.asHttpHeaders().toSingleValueMap().get("OP")));
	}
	
	private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
		
		return (err, request) -> {
			MyIllegalOperation ex = (MyIllegalOperation)err;
			MyExceptionResponse errResp = new MyExceptionResponse();
			errResp.setErrMessage(ex.getMsg());
			errResp.setErrCode(999);
			return ServerResponse.badRequest().bodyValue(errResp);
		};
	}
}
