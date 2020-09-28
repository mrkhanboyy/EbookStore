package com.EBookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqeuest {
	
	private String username;
	private String email;
	private String password;
	private String planName;
	
	
}
