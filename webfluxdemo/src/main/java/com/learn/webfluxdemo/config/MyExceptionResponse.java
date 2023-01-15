package com.learn.webfluxdemo.config;

public class MyExceptionResponse {

	private String errMessage;
	private int errCode;
	
	public MyExceptionResponse() {}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	@Override
	public String toString() {
		return "MyExceptionResponse [errMessage=" + errMessage + ", errCode=" + errCode + "]";
	}
}
