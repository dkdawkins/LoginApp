package com.webencyclop.demo.service;

import com.webencyclop.demo.model.User;
import com.webencyclop.demo.model.UserByEmailAndSecurity;

public interface UserService {

	public void saveUser(User user);
	
	//Added replaceUser to original
	public boolean replaceUser(UserByEmailAndSecurity user);
	
	public boolean isUserAlreadyPresent(User user);
}
