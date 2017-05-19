package com.dev3.app.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev3.app.entity.TextMessage;

public interface ITextMessageRepository extends JpaRepository<TextMessage, Long> {

}
