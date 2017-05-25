package com.dev3.app.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev3.app.entity.Suggestion;
import com.dev3.app.entity.SuggestionReply;

public interface ISuggestionReplyRepository extends JpaRepository<SuggestionReply, Integer> {

}
