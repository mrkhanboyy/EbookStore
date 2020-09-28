package com.EBookStore.service;

import com.EBookStore.dto.AuthenticationResponse;
import com.EBookStore.dto.LoginrReqeuest;
import com.EBookStore.dto.RegisterReqeuest;

public interface AuthService {
	
	public void signUp(RegisterReqeuest req);
	
	public void verifyAccount(String token);
	
	public AuthenticationResponse login(LoginrReqeuest req);
}
