package com.global.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.classes.UserDTO;
import com.global.classes.UserResponse;
import com.global.entities.QuestionIntity;
import com.global.entities.UserIntity;
import com.global.services.UserService;

@RestController
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/insert")
	public UserResponse insertUser(@RequestBody UserDTO user) {
		 return userService.insert(user);
	}
	
	@PutMapping("/update")
	public UserResponse update(@RequestBody UserDTO user) {
		 return userService.update(user);
	}
	
	@GetMapping("/findAll")
	public List<UserIntity> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/find/{id}")
	public UserIntity findById(@PathVariable int id) {
		return userService.findById(id);
	}
	
	@GetMapping("/checkAuthorization")
	public String checkAuthorization(@RequestHeader String authorization) {
		if(userService.checkAuthorization(authorization)) {
			return "Your role is student";
		}else {
			return "Your role is teacher";
		}
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "The user deleted";
	}
	
}
