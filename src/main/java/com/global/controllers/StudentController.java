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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.classes.StudentDTO;
import com.global.classes.UserResponse;
import com.global.entities.QuestionIntity;
import com.global.entities.StudentIntity;
import com.global.entities.StudentIntity;
import com.global.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@PostMapping("/insert")
	public UserResponse insertStudent(@RequestBody StudentIntity teacher) {
		return studentService.insert(teacher);
	}
	
	@PutMapping("/update")
	public UserResponse insertStudent(@RequestBody StudentIntity teacher,@RequestHeader String authentication) throws Exception {
		return studentService.update(teacher,authentication);
	}
	
	@GetMapping("/findById/{id}")
	public StudentIntity findStudent(@PathVariable int id) {
		return studentService.findById(id);
	}
	
	@GetMapping("/findAll")
	public List<StudentIntity> findStudents() {
		return studentService.findAll();
	}
	
	@GetMapping("/findAllByTeacherName")
	public List<StudentDTO> findStudentsByTeacherName(@RequestParam String teacherName) {
		return studentService.findAllByTeacherName(teacherName);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable int id,@RequestHeader String authentication) {
		return studentService.delete(id, authentication);
	}
	
}
