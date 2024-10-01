package com.global.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.classes.AnswerDTO;
import com.global.classes.QuestionDTOAsStudent;
import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.entities.AnswerIntity;
import com.global.entities.QuestionIntity;
//import com.global.services.AnswerService;
import com.global.services.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	AnswerService ansService;
	
	@PostMapping("/insert")
	public String insert(@RequestBody AnswerDTO answer,@RequestHeader String authentication) throws Exception {
		ansService.insert(answer,authentication);
		return "Your answer recorded";
	}
	
	@PutMapping("/update")
	public String updateAnswer(@RequestBody AnswerDTO answer,@RequestHeader String authentication) throws Exception {
		ansService.update(answer,authentication);
		return "Your answer updated";
	}
	
	@GetMapping("/findQuestion")
	public List<QuestionDTOAsStudent>findQuestionsAsStudent(@RequestHeader String authentication) throws Exception{
		return ansService.findQuestionsAsStudent(authentication);
	}
	
	@GetMapping("/findAnswersByDetails")
	public List<StudentResponse> findAllByDetails(@RequestHeader String authentication) throws Exception {
		return ansService.findAllByDetails(authentication);
	}
	
	@GetMapping("/findresults")
	public List<StudentResponseTopFail> findAll(@RequestHeader String authentication) throws Exception {
		return  ansService.findAllStudents(authentication);
	}
	
	@GetMapping("/findTopStudents")
	public List<StudentResponseTopFail> findTop(@RequestParam int numberOfStudents,@RequestParam int successDegree,@RequestHeader String authentication) throws Exception {
		return  ansService.findTopStudents(numberOfStudents,successDegree,authentication);
	}
	
	@GetMapping("/findFailStudents")
	public List<StudentResponseTopFail> findFail(@RequestParam int successDegree,@RequestHeader String authentication) throws Exception {
		return  ansService.findFailStudents(successDegree,authentication);
	}


}

