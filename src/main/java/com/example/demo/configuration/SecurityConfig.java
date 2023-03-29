package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf()
	      .disable()
	      .authorizeRequests()
	      .requestMatchers(HttpMethod.GET,"/api/**")
	      .permitAll()
	      .anyRequest()
	      .authenticated()
	      .and()
	      .httpBasic();
	    return http.build();    
    }
 
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		UserDetails jeison = org.springframework.security.core.userdetails.User.builder().username("jeison")
				.password(passwordEncoder().encode("1234")).roles("USER").build();
		manager.createUser(jeison);
		UserDetails admin = org.springframework.security.core.userdetails.User.builder().username("admin")
				.password(passwordEncoder().encode("admin")).roles("ADMIN").build();
		
		manager.createUser(admin);
		
	    return manager;
	}
	
 
 
}