package com.global.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.classes.Authentication;
import com.global.entities.TeacherIntity;
import com.global.entities.YearIntity;
import com.global.repositories.YearRepository;

@Service
public class YearService {

	@Autowired
	YearRepository yearRepo;
	
	Authentication auth=new Authentication();
	
	public Optional <YearIntity> findYear(int id) {
		return yearRepo.findById(id);
	}
	
	public List<YearIntity> findYearAsTeacher(String authentication) throws Exception {
		if(auth.decriptTeacher(authentication) instanceof TeacherIntity ) {
			TeacherIntity teacher=auth.decriptTeacher(authentication);
			return (List<YearIntity>) yearRepo.findYear(teacher.getId());
		}else {
			throw new Exception("Not Allowed");
		}
		
	}
	
	
}
