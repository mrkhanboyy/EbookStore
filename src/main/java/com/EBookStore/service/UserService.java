package com.EBookStore.service;

import org.springframework.stereotype.Service;

import com.EBookStore.dto.UserDto;
import com.EBookStore.exceptions.UserNotFoundException;
import com.EBookStore.mapper.UserMapper;
import com.EBookStore.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class UserService {

	private final UserMapper userMapper;
	private final UserRepo userRepo;
	
	public UserDto getProfile(String username) {
		
		return userRepo.findByUsername(username).map(userMapper::UserToDto)
				.orElseThrow(() -> new UserNotFoundException("user not found with username : "+username));
	}
}
