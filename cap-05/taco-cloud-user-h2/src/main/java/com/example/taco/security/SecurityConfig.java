package com.example.taco.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.taco.User;
import com.example.taco.data.UserRepository;

@Configuration
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
					.authorizeRequests()
						.antMatchers("/design", "/orders").hasRole("USER")
						.antMatchers("/", "/**").permitAll()
				.and()
					.formLogin()
						.loginPage("/login")
			    .and()
			        .logout()
			        	.logoutSuccessUrl("/")
			    // Make H2-Console non-secured; for debug purposes
		        .and()
		           .csrf()
		           		.ignoringAntMatchers("/h2-console/**")
		        // Allow pages to be loaded in frames from the same origin; needed for H2-Console
		        .and()
		           .headers()
		           .frameOptions()
		           .sameOrigin()
			   .and()
				   .build();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			if (user != null) {
				return user;
			}
			throw new UsernameNotFoundException("User" + username + "not found");
		};
	}
}
