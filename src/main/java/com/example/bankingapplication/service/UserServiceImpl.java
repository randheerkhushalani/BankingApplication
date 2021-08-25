package com.example.bankingapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapplication.model.UserLoginDetails;
import com.example.bankingapplication.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserLoginDetails addUserLoginDetails(UserLoginDetails userLoginDetails) {
		return userRepository.save(userLoginDetails);
	}

}
