package com.EBookStore.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.EBookStore.security.JWTAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;

	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/auth/**",
						 "/api/check/**")
						.permitAll()
			.antMatchers(HttpMethod.GET, "/api/book/books",
										"/api/book/id/**",
					     				 "/api/book/genre/**")
						 					.hasAnyAuthority("BASIC","VIP","PREMIUM")
			.antMatchers(HttpMethod.POST, "/api/book/add")
					     					.hasAnyAuthority("ADMIN")				
			.anyRequest()
			.authenticated();
		
		http
			.addFilterBefore(jwtAuthenticationFilter,
					UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
}
