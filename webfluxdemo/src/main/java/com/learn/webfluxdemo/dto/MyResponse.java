package com.learn.webfluxdemo.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
public class MyResponse {

	private Date date = new Date();
	private int result;
	
	public MyResponse() {}
	
	public MyResponse(int result){
		this.result = result;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Response [date=" + date + ", result=" + result + "]";
	}
}
