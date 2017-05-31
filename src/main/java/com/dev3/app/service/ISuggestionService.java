package com.dev3.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev3.app.entity.Suggestion;


public interface ISuggestionService {
	
	public int addSuggestion(Suggestion suggestion);
	
	public Suggestion getSuggestion(int suggestionId);
	
	public Page<Suggestion> getSuggestions(Pageable pageable);
	
	public void removeSuggestion(int suggestionId);
	
	public void updateSuggestionStatus(int suggestionId, int status);
	
	public void addReply(int suggestionId, String reply);
	
}
