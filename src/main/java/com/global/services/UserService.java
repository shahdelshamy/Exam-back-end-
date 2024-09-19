package com.global.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.classes.UserDTO;
import com.global.classes.UserResponse;
import com.global.entities.UserIntity;
import com.global.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public UserResponse insert(UserDTO user) {
		
		UserIntity userIntity=new UserIntity();
		
		userIntity.setAge(user.getAge());
		userIntity.setEmail(user.getEmail());
		userIntity.setName(user.getName());
		userIntity.setPassword(user.getPassword());
		userIntity.setPhone(user.getPhone());
		userIntity.setIsStudent(user.getIsStudent());
		if(user.getIsStudent()) {
			userIntity.setToken(user.getEmail().replaceAll("@gmail.com", user.getPassword()+"S"));
		}
		else {
			userIntity.setToken(user.getEmail().replaceAll("@gmail.com", user.getPassword()+"T"));
		}
		
		userRepo.save(userIntity);
		return userRepo.getToken(userIntity.getId());
	}
	
	public UserResponse update(UserDTO user) {
		if(userRepo.findById(user.getId()) !=null){
			return this.insert(user);
		}
		return null;
	}
	
	public List<UserIntity> findAll() {
		return (List<UserIntity>) userRepo.findAll();
	}
	
	public UserIntity findById(int id) {
	    return userRepo.findById(id).orElseThrow(
	        () -> new RuntimeException("Student not found")
	    );
	}
	
	public boolean checkAuthorization(String auth) {
		return userRepo.checkAuthorization(auth);
	}
	
	public void delete(int id) {
		userRepo.deleteById(id);
	}
	
	
}
