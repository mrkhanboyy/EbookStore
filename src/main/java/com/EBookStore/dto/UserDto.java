package com.EBookStore.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.EBookStore.model.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String email;
	private LocalDate joinDate;
	private Plan plan;
	private int remainingDays;
}
