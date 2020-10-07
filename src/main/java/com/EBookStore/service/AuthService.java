package com.EBookStore.service;

import javax.validation.Valid;

import com.EBookStore.dto.AuthenticationResponse;
import com.EBookStore.dto.JwtSessionTokenRequest;
import com.EBookStore.dto.LoginReqeuest;
import com.EBookStore.dto.RegisterReqeuest;

public interface AuthService {
	
	public void signUp(RegisterReqeuest req);
	
	public void verifyAccount(String token);
	
	public AuthenticationResponse login(LoginReqeuest req);

	public AuthenticationResponse sessionToken(@Valid JwtSessionTokenRequest jwtSessioTokenRequest);
	
}
