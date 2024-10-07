package com.global.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.classes.StudentDTO;
import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.classes.TeacherDTO;
import com.global.classes.UserResponse;
import com.global.entities.TeacherIntity;
import com.global.entities.YearIntity;
import com.global.services.StudentService;
import com.global.services.TeacherService;
import com.global.services.YearService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	YearService yearService;
	
	@PostMapping("/insert")
	public UserResponse insertTeacher(@RequestBody TeacherIntity teacher) throws Exception {
		return teacherService.insert(teacher);
	}
	
	@PutMapping("/update")
	public UserResponse insertTeacher(@RequestBody TeacherIntity teacher,@RequestHeader String authentication) throws Exception {
		return teacherService.update(teacher,authentication);
	}

	
	@GetMapping("/findById/{id}")
	public Optional<TeacherIntity> findTeacher(@PathVariable int id) {
		return teacherService.findById(id);
	}
	
	@GetMapping("/findAll")
	public List<TeacherIntity> findTeachers() {
		return teacherService.findAll();
	}
	
	@GetMapping("/findTeachers")
	public List<TeacherDTO>findTeachersAsStudent(@RequestHeader String authentication) throws Exception{
		return teacherService.findTeachersAsStudent(authentication);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTeacher(@PathVariable int id,@RequestHeader String authentication) {
		return teacherService.delete(id, authentication);
	}
	
	
	    //Teacher
	@GetMapping("/findAnswersByDetails")
	public List<StudentResponse> findAllByDetails(@RequestHeader String authentication,@RequestHeader String year) throws Exception {
		return teacherService.findAllByDetails(authentication,year);
	}
	@GetMapping("/findYears")
	public List<YearIntity> findYear(@RequestHeader String authentication) throws Exception {
		return yearService.findYearAsTeacher(authentication);
	}
	
	@GetMapping("/findResultsOfAll")
	public List<StudentResponseTopFail> findAll(@RequestHeader String authentication) throws Exception {
		return  teacherService.findAllReasultOfStudents(authentication);
	}
	
	@GetMapping("/findResultsByYear")
	public List<StudentResponseTopFail> findAllByYear(@RequestHeader String authentication,@RequestHeader String year) throws Exception {
		return  teacherService.findAllReasultOfYear(authentication,year);
	}
	
	@GetMapping("/findTopStudents")
	public List<StudentResponseTopFail> findTop(@RequestParam int numberOfStudents,@RequestParam int successDegree,@RequestHeader String authentication,@RequestHeader String year) throws Exception {
		return  teacherService.findTopStudents(numberOfStudents,successDegree,authentication,year);
	}
	
	@GetMapping("/findFailStudents")
	public List<StudentResponseTopFail> findFail(@RequestParam int successDegree,@RequestHeader String authentication,@RequestHeader String year) throws Exception {
		return  teacherService.findFailStudents(successDegree,authentication,year);
	}
	
	@GetMapping("/findAllStudents")
	public List<StudentDTO> findStudentsByTeacherName(@RequestHeader String authentication) throws Exception {
		return studentService.findAllStudentsAsTeacher(authentication);
	}
	
	@GetMapping("/findStudentsOfYear")
	public List<StudentDTO> findStudentsOfYear(@RequestHeader String authentication,@RequestHeader String year) throws Exception {
		return studentService.findStudentsOfYearAsTeacher(authentication,year);
	}
	
}
