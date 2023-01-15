package com.learn.webfluxdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

	@Autowired
	RequestHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> serverResponseRouterFunctionResponse() {
		System.out.println("Inside Webflux router config..");
		return RouterFunctions.route()
					.GET("/router/square/{input}", req -> handler.squareFromRouter(req))
					.GET("router/table/{input}", handler::tableFromRouter)
					.GET("/router/stream/table/{input}", handler::streamTableFromRouter)
					.build();
	}
}
