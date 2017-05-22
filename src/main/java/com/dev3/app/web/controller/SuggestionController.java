package com.dev3.app.web.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev3.app.entity.Suggestion;
import com.dev3.app.service.ISuggestionService;
import com.dev3.app.util.Logger;
import com.dev3.app.web.PageWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	
	@RequestMapping(value="/doCreateSuggestion", method=RequestMethod.POST,  produces="application/text")
	@ResponseBody
	public String createSuggestion(@RequestParam(name="txtSuggestion", defaultValue="")String suggestion){
		
		System.out.println("calling createSuggestion");
		
		Suggestion suggestionEntity = new Suggestion();
		suggestionEntity.setContent(suggestion);
		suggestionEntity.setCreateDate(new Date());
		suggestionEntity.setStatus(Suggestion.STATUS_NEW);
		
		int id = suggestionService.addSuggestion(suggestionEntity);

		Logger.debug("id is : " + id);
		
		return "ok";
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
