package com.learn.webfluxdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.webfluxdemo.dto.MyResponse;
import com.learn.webfluxdemo.service.MathService;

@RestController
@RequestMapping("math")
public class MathController {

	@Autowired
	private MathService mathService;
	
	@GetMapping("square/{input}")
	public MyResponse square(@PathVariable int input) {
		return mathService.square(input);
	}
	
	@GetMapping("table/{input}")
	public List<MyResponse> mathTable(@PathVariable int input){
		return mathService.mathTable(input);
	}
}
