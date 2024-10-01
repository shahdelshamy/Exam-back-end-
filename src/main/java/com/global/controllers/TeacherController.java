package com.global.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.classes.UserResponse;
import com.global.entities.TeacherIntity;
import com.global.services.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	
	@PostMapping("/insert")
	public UserResponse insertTeacher(@RequestBody TeacherIntity teacher) {
		return teacherService.insert(teacher);
	}
	
	@PutMapping("/update")
	public UserResponse insertTeacher(@RequestBody TeacherIntity teacher,@RequestHeader String authentication) throws Exception {
		return teacherService.update(teacher,authentication);
	}
	
	@GetMapping("/findById/{id}")
	public TeacherIntity findTeacher(@PathVariable int id) {
		return teacherService.findById(id);
	}
	
	@GetMapping("/findAll")
	public List<TeacherIntity> findTeachers() {
		return teacherService.findAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable int id,@RequestHeader String authentication) {
		return teacherService.delete(id, authentication);
	}
	
	
	
	
}
