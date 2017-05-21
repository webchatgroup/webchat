package com.dev3.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev3.app.entity.Suggestion;
import com.dev3.app.entity.TextMessage;
import com.dev3.app.repositoriy.ITextMessageRepository;
import com.dev3.app.service.ISuggestionService;

@Controller
public class SuggestionController {
	
	private ISuggestionService suggestionService;
	
	@Autowired
	public SuggestionController(ISuggestionService suggestionService) {
		
		this.suggestionService = suggestionService;
		
	}
	
	@RequestMapping("/suggestionList")
	public String suggestionList(@RequestParam(value="name", required=false, defaultValue="World") String name,Model model) {
		
		
		List<Suggestion> suggestions = suggestionService.getSugggestions(null);
		
		model.addAttribute("suggestions", suggestions);
		
		return "suggestionList"; 
	}
	
}
