package com.webencyclop.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

//NOTE: This is a local entity only; it is not stored in the database
//Entity added to original code
@Entity
@Table(name="user_by_email_and_security")
public class UserByEmailAndSecurity {

	@Id
	@NotNull(message="Email is required")
	@Email(message="Email is invalid")
	@Column(name="email")
	private String email;
	
	@NotNull(message="Security question is required")
	@Column(name="security_question")
	private String securityQuestion;
	
	@NotNull(message="Security answer is required")
	@Column(name="security_answer")
	private String securityAnswer;
	
	@NotNull(message="New Password is required")
	@Length(min=5, message="New Password must be at least 5 characters")
	@Column(name = "password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
}
