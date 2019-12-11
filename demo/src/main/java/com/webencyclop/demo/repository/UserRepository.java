package com.webencyclop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webencyclop.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//Added to original
	public List<User> findByEmailAndSecurityQuestionAndSecurityAnswer(String email, String securityQuestion, String securityAnswer);
	
}
