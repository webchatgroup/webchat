package com.dev3.app.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev3.app.entity.Suggestion;
import com.dev3.app.service.ISuggestionService;
import com.dev3.app.web.PageWrapper;

/**
 * @author kingswood
 */
@Controller
public class SuggestionController {
	
	private ISuggestionService suggestionService;
	
	@Autowired
	public SuggestionController(ISuggestionService suggestionService) {
		
		this.suggestionService = suggestionService;
		
	}
	
	
	@RequestMapping("/doCreateSuggestion")
	public String createSuggestion(){
		
		System.out.println("calling createSuggestion");
		
		return null;
	}
	
	@RequestMapping("/suggestionList")
	public String suggestionList(@PageableDefault(value = 10)Pageable pageable,Model model) {
		
		
		Page<Suggestion> p = suggestionService.getSuggestions(pageable);
		
		List<Suggestion> suggestions = new ArrayList<>();
		
		Iterator<Suggestion> it = p.iterator();
		
		while(it.hasNext()){
			suggestions.add(it.next());
		}
		
		PageWrapper<Suggestion> pageWrapper = new PageWrapper<>(p, "suggestionList");
		
		model.addAttribute("suggestions", suggestions);
		model.addAttribute("page", pageWrapper);
		
		return "suggestionList"; 
	}
	
}
