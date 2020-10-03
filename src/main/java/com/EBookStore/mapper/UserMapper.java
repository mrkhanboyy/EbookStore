package com.EBookStore.mapper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import com.EBookStore.dto.UserDto;
import com.EBookStore.model.Plan;
import com.EBookStore.model.User;

@Component
public class UserMapper {

	public UserDto UserToDto(User user) {
		UserDto ud = new UserDto();
		ud.setUsername(user.getUsername());
		ud.setEmail(user.getEmail());
		ud.setJoinDate(user.getJoinDate());
		ud.setId(user.getId());
		
		Plan plan = new Plan();
		plan.setPlanName(user.getPlan().getPlanName());
		plan.setPlanStartDate(user.getPlan().getPlanStartDate());
		
		
		if(user.getPlan().getPlanEndDate()!=null) {
			plan.setPlanEndDate(user.getPlan().getPlanEndDate());
			ud.setRemainingDays(
					(int)ChronoUnit.DAYS.between(LocalDate.now(),
							user.getPlan().getPlanEndDate()));
		}
		ud.setPlan(plan);
		
		return ud;
	}
	
}
