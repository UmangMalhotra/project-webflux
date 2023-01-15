package com.learn.webfluxdemo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.learn.webfluxdemo.dto.MyResponse;
import com.learn.webfluxdemo.util.SleepUtil;

@Service
public class MathService {
	
	public MyResponse square(int input) {
		return new MyResponse(input * input);
	}
	
	public List<MyResponse> mathTable(int input){
		return IntStream.rangeClosed(1, 10)
					.peek(number -> SleepUtil.sleep(1))
					.peek(number -> System.out.println("Processing number: "+ number))
					.mapToObj(number -> new MyResponse(number * input))
					.collect(Collectors.toList());
	}
}
