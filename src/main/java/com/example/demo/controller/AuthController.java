package com.example.demo.controller;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.entities.Role;
import com.example.demo.entities.Usuario;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/iniciarSesion")
	public ResponseEntity<String> authenticaticateUser(@RequestBody LoginDTO loginDTO) {
		System.out.println("Pedi sesion");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("Ha iniciado sesion correctamente",HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){
		if(userRepository.existsByUsername(registerDTO.getUsername())) {
			return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByUsername(registerDTO.getEmail())) {
			return new ResponseEntity<>("El email ya existe", HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario();
		usuario.setName(registerDTO.getName());
		usuario.setEmail(registerDTO.getEmail());
		usuario.setUsername(registerDTO.getUsername());
		usuario.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		
		Role roles = roleRepository.findByName("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		userRepository.save(usuario);
		
		return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
	}
	

}
