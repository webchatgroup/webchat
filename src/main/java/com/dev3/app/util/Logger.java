package com.dev3.app.util;

public class Logger {
	
			
	
	public Logger(){
	}
	
	
	public static void debug(Object msg){
		System.out.println(msg);
	}
	
	public static void error(Object msg){
		debug(msg);
	}
	
}
