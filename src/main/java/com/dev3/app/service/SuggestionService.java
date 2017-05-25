package com.dev3.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dev3.app.entity.Suggestion;
import com.dev3.app.entity.SuggestionReply;
import com.dev3.app.repositoriy.ISuggestionReplyRepository;
import com.dev3.app.repositoriy.ISuggestionRepository;

@Service
public class SuggestionService implements ISuggestionService {

	private ISuggestionRepository suggestionRepository;
	private ISuggestionReplyRepository suggestionReplyRepository;
	
	public SuggestionService(ISuggestionRepository suggestionRepository, ISuggestionReplyRepository suggestionReplyRepository) {
		this.suggestionRepository = suggestionRepository;
		this.suggestionReplyRepository = suggestionReplyRepository;
	}
	
	@Override
	public int addSuggestion(Suggestion suggestion) {
		
		Suggestion newSuggestion = this.suggestionRepository.save(suggestion);
		
		return newSuggestion.getId();
	}

	@Override
	public Suggestion getSuggestion(int suggestionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSuggestion(int suggestionId) {
		
		Suggestion suggestionToBeDeleted = new Suggestion();
		suggestionToBeDeleted.setId(suggestionId);
		
		suggestionRepository.delete(suggestionToBeDeleted);
	}

	@Override
	public List<Suggestion> getSugggestions(String sortBy) {
		
		
		
		return suggestionRepository.findAll();//TODO sorting
		
	}

	@Override
	public Page<Suggestion> getSuggestions(Pageable pageable) {
		
		Page<Suggestion> p = suggestionRepository.findAll(pageable);
		
		return p; 
		
	}

	@Override
	public void addReply(int suggestionId, String reply) {
		
		SuggestionReply suggestionReply = new SuggestionReply();
		
		Suggestion suggestion = suggestionRepository.findOne(suggestionId);
		
		suggestionReply.setSuggestion(suggestion);
		suggestionReply.setContent(reply);
		suggestionReply.setCreateDate(new Date());
		
		suggestionReplyRepository.save(suggestionReply);
		
	}

	@Override
	public void updateSuggestionStatus(int suggestionId, int status) {
		
		Suggestion suggestion = suggestionRepository.findOne(suggestionId);
		suggestion.setStatus(status);
		
		suggestionRepository.save(suggestion);
		
	}

}
