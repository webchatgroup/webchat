package com.volvo.it.vgas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volvo.it.vgas.entities.TextMessage;

public interface TextMessageRepository extends JpaRepository<TextMessage,Long>{

}
