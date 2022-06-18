package com.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hroauth.entities.User;
import com.hroauth.feignclients.UserFeigClient;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeigClient userFeigClient;
	
	public User findByEmail(String email) {
		User user = userFeigClient.findByEmail(email).getBody();
		
		if(user == null) {
			logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		logger.info("Email encontrado: "+email);
		return user;
	}
	
}
