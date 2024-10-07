package com.global.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.classes.Authentication;
import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.classes.TeacherDTO;
import com.global.classes.UserResponse;
import com.global.entities.StudentIntity;
import com.global.entities.TeacherIntity;
import com.global.entities.YearIntity;
import com.global.repositories.TeacherRepositry;
import com.google.gson.Gson;

import jakarta.transaction.Transactional;

@Service
public class TeacherService {

	@Autowired
	TeacherRepositry teacherRepo;
	
	@Autowired
	YearService yearService;
	
	Authentication auth=new Authentication();
	
	public UserResponse insert(TeacherIntity teacher) throws Exception {
	
		if(auth.checkEmailAndPhone(teacher.getEmail(),teacher.getPhone())) {
			
			List <YearIntity> yearList=new ArrayList<>();
			
			for(int i=0;i<teacher.getYears().size();i++) {
				Optional<YearIntity>year=yearService.findYear(teacher.getYears().get(i).getId());
				System.out.println(year.get());
				if(year.isPresent()) {
					yearList.add(year.get());
				}else {
					new RuntimeException("This Year Not Found");
				}
			}

			teacher.setYears(yearList);
			teacherRepo.save(teacher);
			
			String encriptedToken=auth.encript(teacher);
			
			UserResponse user=new UserResponse();
			user.setId(teacherRepo.selectId(teacher.getEmail(), teacher.getPassword()));
			user.setToken(encriptedToken);
			
			return user;
		}else {
			throw new Exception("The formate of Email or phone number not corect");
		}
		
	}
	
	public UserResponse update(TeacherIntity teacher,String authentication) throws Exception {
		if(auth.decriptTeacher(authentication).getId() == teacher.getId()) {
			if(this.findById(teacher.getId())!=null) {
				return this.insert(teacher);
			}
		}else {
			 throw new Exception("Not Allowed");
		}
		return null;
	}
	
	public Optional<TeacherIntity> findById(int id) {
		return teacherRepo.findById(id);
	}
	
	
	public List<TeacherDTO> findTeachersAsStudent(String authentication) throws Exception{
		if(auth.decriptStudent(authentication) instanceof StudentIntity) {
			return teacherRepo.findTeacherAsStudent(auth.decriptStudent(authentication).getId());
		}else {
			throw new Exception("Not Allowed");
		}
	}
	
	public List<TeacherIntity> findAll() {
		return (List<TeacherIntity>) teacherRepo.findAll();
	}
	
	public List <YearIntity>findYearsById(int id){
		return teacherRepo.findYearsById(id);
	}
	
	public String delete(int id,String authentication) {
		if(auth.decriptTeacher(authentication).getId() == id) {
			if(this.findById(id)!=null) {
				teacherRepo.deleteById(id);
				return "Teacher deleted";
			}else {
				return "The Teacher Not Found";
			}
		}
		return "Not Allowed";
	}
	
	
	
	    //Teacher
	public List<StudentResponse> findAllByDetails(String authentication,String year) throws Exception {
		if(auth.decriptTeacher(authentication) instanceof TeacherIntity ) {
			TeacherIntity teacher=auth.decriptTeacher(authentication);
			return (List<StudentResponse>) teacherRepo.findAllByDetails(teacher.getName(),year);
		}else {
			throw new Exception("Not Allowed");
		}
		
	}
	
	
	
	public List<StudentResponseTopFail> findAllReasultOfStudents(String authentication) throws Exception {
		if(auth.decriptTeacher(authentication) instanceof TeacherIntity) {
			TeacherIntity teacher=auth.decriptTeacher(authentication);
			return teacherRepo.findAllReasultOfStudents(teacher.getId());   //getContent to return list
		}else {
			throw new Exception("the authoriation auth not valid");
		}
		
	}

	public List<StudentResponseTopFail> findAllReasultOfYear(String authentication,String year) throws Exception {
		if(auth.decriptTeacher(authentication) instanceof TeacherIntity) {
			TeacherIntity teacher=auth.decriptTeacher(authentication);
			return teacherRepo.findResultsByYear(teacher.getId(),year);   //getContent to return list
		}else {
			throw new Exception("the authoriation auth not valid");
		}
		
	}
	
	
	public List<StudentResponseTopFail> findTopStudents(int limit,int successDegree,String authentication,String year) throws Exception {
		if(auth.decriptTeacher(authentication) instanceof TeacherIntity) {
			
			TeacherIntity teacher=auth.decriptTeacher(authentication);
			
			Pageable pageable=PageRequest.of(0, limit);
			return teacherRepo.findTopStudents(pageable,successDegree,teacher.getName(),year).getContent();   //getContent to return list
		}else {
			throw new Exception("the authoriation auth not valid");
		}
		
		
	}
	
	public List<StudentResponseTopFail> findFailStudents(int successDegree,String authentication,String year) throws Exception {
		if(auth.decriptTeacher(authentication) instanceof TeacherIntity) {
			
			TeacherIntity teacher=auth.decriptTeacher(authentication);
			
			return teacherRepo.findFailStudents(successDegree,teacher.getName(),year);  
		}else {
			throw new Exception("the authoriation auth not valid");
		}
		
	}

	
	
}
