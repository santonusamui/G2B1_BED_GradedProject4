package com.gl.emra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	// Authentication - Entry Method
	protected void configure(AuthenticationManagerBuilder auth) {

		auth.authenticationProvider(emraDaoAuthenticationProvider());
	}

	// Username
	@Bean
	public UserDetailsService emraUserDetailsService() {
		return new EmraUserDetailsService();
	}

	// Password
	@Bean
	public PasswordEncoder emraPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Username + password
	@Bean
	public DaoAuthenticationProvider emraDaoAuthenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(emraUserDetailsService());
		provider.setPasswordEncoder(emraPasswordEncoder());

		return provider;
	}

	// Authorization

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/roles", "/users").permitAll().and().authorizeRequests()
				.antMatchers("/employees/**").authenticated().and().httpBasic();

		return http.build();

	}

}
