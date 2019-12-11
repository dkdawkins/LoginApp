package com.webencyclop.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webencyclop.demo.model.Role;
import com.webencyclop.demo.model.User;
import com.webencyclop.demo.model.UserByEmailAndSecurity;
import com.webencyclop.demo.repository.RoleRepository;
import com.webencyclop.demo.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	//Added replaceUser to original code
	@Override
	public boolean replaceUser(UserByEmailAndSecurity user) {
		
		List<User> returnedList = userRepository.findByEmailAndSecurityQuestionAndSecurityAnswer(
				user.getEmail(),
				user.getSecurityQuestion(),
				user.getSecurityAnswer());
		
		if(returnedList == null) {
			return false;
		}
		
		//WARNING: assumes only one user is returned by query;
		//.delete does not work under current implementation, as a foreign key prevents the deletion
		User foundUser = returnedList.get(0);
		userRepository.delete(foundUser);	//Delete user with old password
		
		//Replace original password and save new info to database
		foundUser.setPassword(encoder.encode(user.getPassword()));
		foundUser.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("SITE_USER");
		foundUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(foundUser);
		
		return true;
	}
	
	@Override
	public boolean isUserAlreadyPresent(User user) {
		//TODO: implement this
		return false;
	}

}
