package com.EBookStore.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.EBookStore.dto.SubscriptionDto;
import com.EBookStore.exceptions.UserNotFoundException;
import com.EBookStore.model.User;
import com.EBookStore.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	private final UserRepo userRepo;
	
	@Override
	public void upgradePlan(SubscriptionDto subDto) {
		User user = userRepo.findByUsername(subDto.getUsername())
				.orElseThrow(() -> new UserNotFoundException("user not found with username : "+subDto.getUsername()));
		
		if(subDto.getPlanName().trim().equals("VIP")) {
			user.getPlan().setPlanName("VIP");
			user.getPlan().setPlanStartDate(LocalDate.now());
			user.getPlan().setPlanEndDate(LocalDate.now().plusDays(30));
			user.setRole("VIP");
		}else if(subDto.getPlanName().trim().equals("PREMIUM")) {
			user.getPlan().setPlanName("PREMIUM");
			user.getPlan().setPlanStartDate(LocalDate.now());
			user.getPlan().setPlanEndDate(LocalDate.now().plusDays(365));
			user.setRole("PREMIUM");
		}
		userRepo.save(user);
	}

	@Override
	public void degradePlan(String username) {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user not found with username : "+username));
		user.getPlan().setPlanName("FREE");
		user.getPlan().setPlanStartDate(LocalDate.now());
		user.getPlan().setPlanEndDate(null);
		user.setRole("BASIC");
		userRepo.save(user);
	}

}
