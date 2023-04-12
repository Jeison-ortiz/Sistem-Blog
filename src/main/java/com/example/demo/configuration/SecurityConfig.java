package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
            .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")  
            .anyRequest().authenticated()
            .and()
            .httpBasic();   
		return http.build();
    }
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}
 
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		UserDetails jeison = org.springframework.security.core.userdetails.User.builder().username("jeison")
//				.password(passwordEncoder().encode("1234")).roles("USER").build();
//		manager.createUser(jeison);
//		UserDetails admin = org.springframework.security.core.userdetails.User.builder().username("admin")
//				.password(passwordEncoder().encode("holi")).roles("ADMIN").build();
//		
//		manager.createUser(admin);
//		
//	    return manager;
//	}
	
}

