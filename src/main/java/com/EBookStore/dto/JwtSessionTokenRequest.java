package com.EBookStore.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSessionTokenRequest {

	@NotBlank(message = "session token cannot be blank")
	private String sessionToken;
	private String username;
}
