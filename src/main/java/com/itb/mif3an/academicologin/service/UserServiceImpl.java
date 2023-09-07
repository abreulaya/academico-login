package com.itb.mif3an.academicologin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itb.mif3an.academicologin.model.Role;
import com.itb.mif3an.academicologin.model.User;
import com.itb.mif3an.academicologin.repository.RoleRepository;
import com.itb.mif3an.academicologin.repository.UserRepository;
import com.itb.mif3an.academicologin.web.dto.UserDto;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired // injeção de dependencia - instancia automaticamente
	private UserRepository userRepository;
	
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(UserDto userDto) {
		
		//Algoritimo para criptografar a senha 
	
		User user = new User(userDto.getFirstName(),
				            userDto.getLastName(), 
				            userDto.getEmail(), 
				            passwordEncoder.encode(userDto.getPassword()), 
				            new ArrayList<>());// construtor 
		
		 userRepository.save (user);
		 this.addRoleToUser(user.getEmail(), "ROLE_USER");
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		
		User user = userRepository.findByEmail(username);
		Role role = roleRepository.findByName (roleName);
		user.getRoles().add(role);
		userRepository.save(user);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	
}

