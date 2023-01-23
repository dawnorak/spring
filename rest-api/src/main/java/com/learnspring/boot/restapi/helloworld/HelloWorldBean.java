package com.learnspring.boot.restapi.helloworld;

public class HelloWorldBean {
		
	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "Hello World Bean [message = " + message + "]";
	}

}
