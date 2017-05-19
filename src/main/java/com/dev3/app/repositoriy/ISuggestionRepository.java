package com.dev3.app.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev3.app.entity.Suggestion;

public interface ISuggestionRepository extends JpaRepository<Suggestion, Integer> {

}
