package com.dev3.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev3.app.entity.TextMessage;

public interface TextMessageRepository extends JpaRepository<TextMessage,Long>{

}
