package com.EBookStore.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.EBookStore.model.User;
import com.EBookStore.repository.UserRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AvailabilityCheckServiceImpl implements AvailabilityCheckService {
	
	private final UserRepo userRepo;
	
	@Override
	public String checkEmailAvailability(String email) {
		String result = "Email Not Available";
		User user = userRepo.findByEmail(email);
		if (user ==  null) {
			result = "Email Available";
		}
		return result;
	}
	
	@Override
	public String checkUsernameAvailability(String username) {
		String result = "Username Not Available";
		Optional<User> user = userRepo.findByUsername(username);
		if (!user.isPresent()) {
			result = "Username Available";
		}
		return result;
	}

}
