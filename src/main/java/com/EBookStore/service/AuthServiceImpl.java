package com.EBookStore.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EBookStore.dto.AuthenticationResponse;
import com.EBookStore.dto.LoginrReqeuest;
import com.EBookStore.dto.RegisterReqeuest;
import com.EBookStore.exceptions.TokenNotFoundException;
import com.EBookStore.exceptions.UserNotFoundException;
import com.EBookStore.model.Mail;
import com.EBookStore.model.Plan;
import com.EBookStore.model.User;
import com.EBookStore.model.VerificationToken;
import com.EBookStore.repository.UserRepo;
import com.EBookStore.repository.VerificationTokenRepository;
import com.EBookStore.security.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepo userRepo;
	private final PasswordEncoder encoder;
	private final MailService mailService;
	private final VerificationTokenRepository verificationTokenRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtGenerator;

	//registering user and adding to database
	@Transactional
	public void signUp(RegisterReqeuest req) {
		
		User user = new User();
		user.setUsername(req.getUsername());
		user.setEmail(req.getEmail());
		user.setJoinDate(LocalDate.now());
		user.setPassword(encoder.encode(req.getPassword()));
	
		Plan plan = new Plan();
		plan.setPlanName(req.getPlanName());
		plan.setPlanStartDate(LocalDate.now());
		
		if(req.getPlanName().equals("FREE")) {
			plan.setPlanEndDate(null);
			user.setRole("BASIC");
		}else if(req.getPlanName().equals("VIP")) {
			plan.setPlanEndDate(LocalDate.now().plusDays(30));
			user.setRole("VIP");
		}else if(req.getPlanName().equals("PREMIUM")) {
			plan.setPlanEndDate(LocalDate.now().plusDays(365));
			user.setRole("PREMIUM");
		}
		
		user.setPlan(plan);
		user.setJoinDate(LocalDate.now());
		user.setEnabled(false);
		
		userRepo.save(user);
		
		String token = generateVerificationToken(user);
		Mail mail = new Mail();
		mail.setSubject("Please Activate your account.");
		mail.setRecepient(user.getEmail());
		mail.setContent("Thankyou for signing up to EBook Store "+
		"please click on below link to activate your account"+
		"http://localhost:9090/api/auth/verify/"+token);
		mailService.sendMail(mail);
	}
	// generating verification token
	@Transactional
	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
       
	}
	
	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        ActivateUser(verificationToken.orElseThrow(() -> new TokenNotFoundException("invalid token")));
	}

	// activating user
	@Transactional
	private void ActivateUser(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getUsername();
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user not found with username : "
				+verificationToken.getUser().getUsername() ));
        user.setEnabled(true);
        userRepo.save(user);
	}

	@Transactional
	public AuthenticationResponse login(LoginrReqeuest req) {
		String username = req.getUsername();
		String password = req.getPassword();
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username,  password));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("user not found with username : "+username));
		
		String token = jwtGenerator.generateToken(user);
		return new AuthenticationResponse(username, token);
	}
	
}
