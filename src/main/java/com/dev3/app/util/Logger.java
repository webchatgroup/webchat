package com.dev3.app.util;

public class Logger {
	
			
	private Class<?> clazz; 
	
	public Logger(Class<?> clazz){
		this.clazz = clazz;
	}
	
	
	public void debug(Object msg){
		System.out.println(clazz.getName() + msg);
	}
	
	public void error(Object msg){
		debug(msg);
	}
	
}
