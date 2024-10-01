package com.global.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.classes.Authentication;
import com.global.classes.UserResponse;
import com.global.entities.TeacherIntity;
import com.global.repositories.TeacherRepositry;
import com.google.gson.Gson;

@Service
public class TeacherService {

	@Autowired
	TeacherRepositry teacherRepo;
	
	Authentication auth=new Authentication();
	
	public UserResponse insert(TeacherIntity teacher) {
		teacherRepo.save(teacher);
		
		Authentication token=new Authentication();
		String encriptedToken=token.encript(new Gson().toJson(teacher));
		
		UserResponse user=new UserResponse();
		user.setId(teacherRepo.selectId(teacher.getEmail(), teacher.getPassword()));
		user.setToken(encriptedToken);
		
		return user;
	}
	
	public UserResponse update(TeacherIntity teacher,String token) throws Exception {
		if(auth.decriptTeacher(token).getId() == teacher.getId()) {
			if(this.findById(teacher.getId())!=null) {
				return this.insert(teacher);
			}
		}else {
			 throw new Exception("Not Allowed");
		}
		return null;
	}
	
	public TeacherIntity findById(int id) {
		return teacherRepo.findById(id).orElseThrow(()-> new RuntimeException("Teacher not fount"));
	}
	
	public List<TeacherIntity> findAll() {
		return (List<TeacherIntity>) teacherRepo.findAll();
	}
	
	public String delete(int id,String token) {
		if(auth.decriptTeacher(token).getId() == id) {
			if(this.findById(id)!=null) {
				teacherRepo.deleteById(id);
				return "Teacher deleted";
			}else {
				return "The Teacher Not Found";
			}
		}
		return "Not Allowed";
	}
	
	
	
}
