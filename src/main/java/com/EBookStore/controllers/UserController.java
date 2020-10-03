package com.EBookStore.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EBookStore.dto.UserDto;
import com.EBookStore.model.User;
import com.EBookStore.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
	
	
	private final UserService userService;
	
	@GetMapping("/me")
	public UserDto getProfileDetails() {
		String username =  SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.getProfile(username);
	}
	
}
