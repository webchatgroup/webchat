package com.dev3.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev3.app.entity.Suggestion;


public interface ISuggestionService {
	
	public int addSuggestion(Suggestion suggestion);
	
	public Suggestion getSuggestion(int suggestionId);
	
	public Page<Suggestion> getSuggestions(Pageable pageable);
	
	public int removeSuggestion(int suggestionId);
	
	public List<Suggestion> getSugggestions(String sortBy);
	
}
