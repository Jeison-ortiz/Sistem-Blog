package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;




@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
            .anyRequest().authenticated()
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
				.password(passwordEncoder().encode("holi")).roles("ADMIN").build();
		
		manager.createUser(admin);
		
	    return manager;
	}
	
 
 
}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	
////	@Autowired
////	private CustomUserDetailsService userDetailsService;
//	
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http.csrf().disable()
//	        .authorizeHttpRequests()
//	            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
//	            .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
//	            .requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
//	            .requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()
//	            .anyRequest().authenticated()
//	        .and()
//	        .httpBasic();
//	    return http.build();
//	
////	@Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////
////		http.csrf().disable()
////			.authorizeHttpRequests()
////			.requestMatchers(HttpMethod.GET,"/api/**")
////			.permitAll()
////			.anyRequest()
////			.authenticated()
////			.and()
////			.httpBasic();
////			return http.build();
////			
////		.and().csrf().disable().build();
////		.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN")
////	      .disable()
////	      .authorizeRequests()
////	      .requestMatchers(HttpMethod.GET,"/api/**")
////	      .permitAll()
////	      .anyRequest()
////	      .authenticated()
////	      .and()
////	      .httpBasic();
////	    return http.build();    
//    }
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails jeison = User.builder().username("jeison")
//				.password(passwordEncoder().encode("12345")).roles("USER").build();
//		UserDetails admin = User.builder().username("admin")
//				.password(passwordEncoder().encode("holi")).roles("ADMIN").build();	
//	    return new InMemoryUserDetailsManager(jeison,admin);
//	}
//	
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////            .authorizeHttpRequests((authz) -> authz
////                .anyRequest().authenticated()
////            )
////            .httpBasic();
////        return http.build();
////    }
//	
// 
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
////		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
////	}
//	
//}