package com.EBookStore.service;

import com.EBookStore.dto.SubscriptionDto;

public interface SubscriptionService {

	public void upgradePlan(SubscriptionDto subDto);
	public void degradePlan(String Username);
}
