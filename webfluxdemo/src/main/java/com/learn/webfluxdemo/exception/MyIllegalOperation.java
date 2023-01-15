package com.learn.webfluxdemo.exception;

public class MyIllegalOperation extends RuntimeException {

	private final String msg;
	
	public MyIllegalOperation(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
