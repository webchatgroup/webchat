package com.dev3.app.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev3.app.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userId=?1")
	User findByUserId(String userId);

}