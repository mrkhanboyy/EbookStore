package com.EBookStore.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(unique = true, name = "username")
	@NotBlank(message = "username is required")
	private String username;
	
	@Email
	@Column(unique = true)
	@NotBlank(message = "email is required")
	private String email;
	
	@NotBlank(message = "password is required")
	@JsonIgnore
	private String password;
	
    private LocalDate joinDate;
	private String Role;
	private Plan plan;
	private boolean enabled;

}
