package com.dev3.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev3.app.entity.User;
import com.dev3.app.repositoriy.IUserRepository;
import com.dev3.app.util.Decoder;

@Service
public class UserService implements IUserService {

	private IUserRepository userRepository;
	
	@Autowired
	public UserService(IUserRepository userRepository) {
	
		this.userRepository = userRepository;
		
	}

	@Override
	public User findByUserId(String userId) {
		
		return userRepository.findByUserId(userId);
	}
	
	/*@Override
	public int verifyPassword(String userId, String password) {
		
		User user = userRepository.findByUserId(userId);
		
		if(null == user || user.getUserId() == null){
			// user id not found
			return RESULT_NO_USER;
		}
		
		String decodedPassword = Decoder.decode(password);
		
		if(!decodedPassword.equals(user.getPassword())){
			
			// wrong password
			
			return RESULT_WRONG_PASSWORD;
		}
		
		return RESULT_OK;
	}*/

}
