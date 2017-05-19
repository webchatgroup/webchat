package com.dev3.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev3.app.entity.Suggestion;
import com.dev3.app.repositoriy.ISuggestionRepository;

@Service
public class SuggestionService implements ISuggestionService {

	private ISuggestionRepository suggestionRepository;
	
	
	public SuggestionService(ISuggestionRepository suggestionRepository) {
		this.suggestionRepository = suggestionRepository;
	}
	
	@Override
	public int addSuggestion(Suggestion suggestion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Suggestion getSuggestion(int suggestionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeSuggestion(int suggestionId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Suggestion> getSugggestions(String sortBy) {
		
		return suggestionRepository.findAll();//TODO sorting
		
	}

}
