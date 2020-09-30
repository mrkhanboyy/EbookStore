package com.EBookStore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EBookStore.dto.SubscriptionDto;
import com.EBookStore.service.SubscriptionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/plan")
@AllArgsConstructor
public class SubscriptionController {
	
	private final SubscriptionService subscriptionService;

	@PutMapping("/upgrade")
	public HttpStatus upgrade(@RequestBody SubscriptionDto subDto) {
		subscriptionService.upgradePlan(subDto);
		return HttpStatus.OK;
	}
	
}
