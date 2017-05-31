package com.dev3.app.service;

import com.dev3.app.entity.User;

public interface IUserService {
	
	public static int RESULT_OK = 0;
	public static int RESULT_NO_USER = 1;
	public static int RESULT_WRONG_PASSWORD = 2;
	
	
	public User findByUserId(String userId);
	
}
