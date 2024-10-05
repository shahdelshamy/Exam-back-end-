package com.global.services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.classes.Authentication;
import com.global.classes.StudentDTO;
import com.global.classes.UserResponse;
import com.global.entities.StudentIntity;
import com.global.entities.TeacherIntity;
import com.global.entities.StudentIntity;
import com.global.repositories.StudentRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	StudentRepository userRepo;
	
	@Autowired
	TeacherService teacher;
	
	private Authentication auth=new Authentication();

	
	public UserResponse insert(StudentIntity student) throws Exception {
		if(auth.checkEmailAndPhone(student.getEmail(), student.getPhone())) {
			List <TeacherIntity> teacherStudent = new ArrayList<>();
			for(int i=0;i<student.getTeachers().size();i++) {
				Optional<TeacherIntity>teacherExist= teacher.findById(student.getTeachers().get(i).getId());
				if(teacherExist.isPresent()) {
					teacherStudent.add(teacherExist.get());
				}else{
					throw new Exception("The teachher not found");
				}
			}
			student.setTeachers(teacherStudent);
			userRepo.save(student);
			
			ObjectMapper objectToString=new ObjectMapper();       //Jackson
			String value=objectToString.writeValueAsString(student);
			
			String encriptedToken=auth.encript(value);
			System.out.println("Serialized JSON: " + encriptedToken);
			
			UserResponse user=new UserResponse();
			
			user.setId(userRepo.selectId(student.getEmail(), student.getPassword()));
			user.setToken(encriptedToken);
			
			return user;
		}else {
			throw new Exception("The formate of Email or phone number not corect");
		}

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
	
	public List<StudentDTO> findAllStudentsAsTeacher(String authentication) throws Exception {
		if(auth.decriptTeacher(authentication) instanceof TeacherIntity) {
			return (List<StudentDTO>) userRepo.findByTeacherName(auth.decriptTeacher(authentication).getName());
		}else {
			throw new Exception("Not Allowed");
		}
		
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
