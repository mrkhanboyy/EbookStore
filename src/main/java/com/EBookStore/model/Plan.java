package com.EBookStore.model;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Plan {
	
	@NotBlank(message = "planName is required")
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;

}
