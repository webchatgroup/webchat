package com.dev3.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev3.app.entity.TextMessage;
import com.dev3.app.repositoriy.ITextMessageRepository;

@Controller
public class SuggestionController {
	
	private ITextMessageRepository iTextMessageRepository;
	
	@Autowired
	public SuggestionController(ITextMessageRepository iTextMessageRepository) {
		
		this.iTextMessageRepository = iTextMessageRepository;
		
	}
	
	@RequestMapping("/suggestionList")
	public String suggestionList(Model model) {
		
		
		List<TextMessage> suggestions = iTextMessageRepository.findAll();
		
		model.addAttribute("suggestions", suggestions);
		
		return "suggestionList"; 
	}
	
}
