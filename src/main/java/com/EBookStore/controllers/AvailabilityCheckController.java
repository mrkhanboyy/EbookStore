package com.EBookStore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EBookStore.service.AvailabilityCheckService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/check")
@AllArgsConstructor
public class AvailabilityCheckController {
	
	private final AvailabilityCheckService availabilityCheckService; 

	@GetMapping("/email/{email}")
	public ResponseEntity<String> checkEmailAvailability(@PathVariable("email") String email) {
		return new ResponseEntity<String> (
				availabilityCheckService.checkEmailAvailability(email), HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<String> checkUsernameAvailability(@PathVariable("username") String username) {
		return new ResponseEntity<String> (
				availabilityCheckService.checkEmailAvailability(username), HttpStatus.OK);
	}
}
