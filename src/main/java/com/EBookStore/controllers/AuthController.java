package com.EBookStore.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EBookStore.dto.AuthenticationResponse;
import com.EBookStore.dto.JwtSessionTokenRequest;
import com.EBookStore.dto.LoginReqeuest;
import com.EBookStore.dto.RegisterReqeuest;
import com.EBookStore.security.SessionTokenUtil;
import com.EBookStore.service.AuthService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final SessionTokenUtil sessionTokenUtil;

	/**
     * Entry point for the user registration process. On successful registration,
     * sends verification email
     */
	@ApiOperation(value = "Registers the user and sends verification email")
	@PostMapping("/register")
	public ResponseEntity<String> signUp(@Valid @RequestBody RegisterReqeuest req) {
		authService.signUp(req);
		return new ResponseEntity<String>("user registration successful",HttpStatus.OK);
	}
	
	/**
     * Confirm the email verification token generated for the user during
     * registration.
     */
	@ApiOperation(value = "Confirms the email verification token that has been generated for the user during registration")
	@GetMapping("verify/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable ("token") String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Verified successfully", HttpStatus.OK);
	}
	
	/**
     * Entry point for the user log in. Return the jwt auth token and the session token
     */
	@ApiOperation(value = "Entry point for the user log in. Return the jwt auth token and the session token")
	@PostMapping("/authenticate")
	public AuthenticationResponse login(@Valid @RequestBody LoginReqeuest req) {
		 return authService.login(req);
	}
	
	/**
     * Refresh the expired jwt token using a session token
     * and return a new jwt token to the caller
     */
	 @ApiOperation(value = "Refresh the expired jwt authentication by issuing a session refresh request and returns the" +
	            "updated jwt tokens")
	@PostMapping("/refresh_token")
	public AuthenticationResponse sessionToken(@Valid @RequestBody JwtSessionTokenRequest jwtSessioTokenRequest) {
		return authService.sessionToken(jwtSessioTokenRequest);
	}
	
	  /**
	   * Entry point for the user logout. delete the session token for a user
	   */
	 @ApiOperation(value = "Entry point for the user logout. delete the session token for a user")
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody JwtSessionTokenRequest jwtSessioTokenRequest) {
		sessionTokenUtil.deleteSessionToken(jwtSessioTokenRequest.getSessionToken());
		return ResponseEntity.status(HttpStatus.OK).body("session token deleted successfully");
	}
	

}
