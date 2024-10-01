package com.global.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.classes.Authentication;
import com.global.classes.StudentDTO;
import com.global.classes.UserResponse;
import com.global.entities.StudentIntity;
import com.global.entities.StudentIntity;
import com.global.repositories.StudentRepository;
import com.google.gson.Gson;

@Service
public class StudentService {

	@Autowired
	StudentRepository userRepo;
	
	private Authentication auth=new Authentication();
	
	public UserResponse insert(StudentIntity student) {
		
		userRepo.save(student);
		
		Authentication token=new Authentication();
		
		String encriptedToken=auth.encript(new Gson().toJson(student));
		
		UserResponse user=new UserResponse();
		
		user.setId(userRepo.selectId(student.getEmail(), student.getPassword()));
		user.setToken(encriptedToken);
		
		return user;
	}
	
	public UserResponse update(StudentIntity student,String token) throws Exception {

		if(auth.decriptStudent(token).getId() == student.getId()) {
			if(this.findById(student.getId())!=null) {
				return this.insert(student);
			}
		}else {
			throw new Exception("Not Allowed");
		}
		
		return null;
	}
	
	public StudentIntity findById(int id) {
		return userRepo.findById(id).orElseThrow(()-> new RuntimeException("Student not fount"));
	}
	
	public List<StudentIntity> findAll() {
		return (List<StudentIntity>) userRepo.findAll();
	}
	
	public List<StudentDTO> findAllByTeacherName(String teacherName) {
		return (List<StudentDTO>) userRepo.findByTeacherName(teacherName);
	}
	

	public String delete(int id,String token) {
		if(auth.decriptStudent(token).getId() == id) {
			if(this.findById(id)!=null) {
				userRepo.deleteById(id);
				return "Teacher deleted";
			}else {
				return "The Teacher Not Found";
			}
		}
		return "Not Allowed";
	}
	
	
	
	 
	
	
}
