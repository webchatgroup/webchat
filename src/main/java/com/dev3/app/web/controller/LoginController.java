package com.dev3.app.web.controller;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dev3.app.entity.User;
import com.dev3.app.repositoriy.IUserRepository;
import com.dev3.app.service.IUserService;
import com.dev3.app.util.Decoder;

/**
 * 
 * @author RL
 *
 */

@Controller
public class LoginController {

	private IUserService userService;
	
	@Autowired
	public LoginController(IUserService userService) {
		
		this.userService = userService;
		
	}
	
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(String userId, String password, Model model, HttpSession session, HttpServletResponse response) {
		
		User user = userService.findByUserId(userId);
		
		if(null == user || user.getUserId() == null){
			// user id not found
			model.addAttribute("errorMsg", "Invalid user id");
			return "login";
		}
		
		String decodedPassword = Decoder.decode(password);
		
		if(!decodedPassword.equals(user.getPassword())){
			
			// wrong password
			model.addAttribute("errorMsg", "Wrong password");
			return "login";
		}
		
		session.setAttribute("user", user);					
		
		return "redirect:suggestionList";
	}
	
	@RequestMapping(value="/doLogout", method = RequestMethod.GET)
	public String doLogout(HttpSession session){
		
		session.removeAttribute("user");
		
		return "login";
	}

}
