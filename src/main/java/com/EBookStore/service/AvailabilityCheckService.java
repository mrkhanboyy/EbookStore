package com.EBookStore.service;

public interface AvailabilityCheckService {

	public String checkEmailAvailability(String email);
	
	public String checkUsernameAvailability(String username);
	
}
