package com.EBookStore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EBookStore.dto.AuthenticationResponse;
import com.EBookStore.dto.LoginReqeuest;
import com.EBookStore.dto.RegisterReqeuest;
import com.EBookStore.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<String> signUp(@RequestBody RegisterReqeuest req) {
		authService.signUp(req);
		return new ResponseEntity<String>("user registration successful",HttpStatus.OK);
	}
	
	@GetMapping("verify/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable ("token") String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Verified successfully", HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public AuthenticationResponse login(@RequestBody LoginReqeuest req) {
		 return authService.login(req);
		
	}
}
