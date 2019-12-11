package com.webencyclop.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "auth_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_user_id")
	private int id;

	@NotNull(message="First name is required")
	@Column(name = "first_name")
	private String name;

	@NotNull(message="Last name is required")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message="Email is required")
	@Email(message="Email is invalid")
	@Column(name = "email")
	private String email;

	@NotNull(message="Password is required")
	@Length(min=5, message="Password must be at least 5 characters")
	@Column(name = "password")
	private String password;
	
	//Added to original code
	////////////////////////////////////////////////////
	@NotNull(message="Security question is required")
	@Column(name = "security_question")
	private String securityQuestion;
	
	@NotNull(message="Security answer is required")
	@Column(name = "security_answer")
	private String securityAnswer;
	///////////////////////////////////////////////////
	
	@Column(name = "status")
	private String status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//Added to original code
	///////////////////////////////////////////////////////////////
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
	///////////////////////////////////////////////////////////////
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}
