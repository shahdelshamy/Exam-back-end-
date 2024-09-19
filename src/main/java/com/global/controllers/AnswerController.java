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
import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.entities.AnswerIntity;
import com.global.services.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	AnswerService ansService;
	
	@PostMapping("/insert")
	public String insert(@RequestBody AnswerDTO answer,@RequestHeader String authorization) throws Exception {
		ansService.insert(answer,authorization);
		return "Your answer recorded";
	}
	
	@PutMapping("/update")
	public String updateAnswer(@RequestBody AnswerDTO answer,@RequestHeader String authorization) throws Exception {
		ansService.update(answer,authorization);
		return "Your answer updated";
	}
	

	@GetMapping("/findAllByDetails")
	public List<StudentResponse> findAllByDetails(@RequestHeader String authorization) throws Exception {
		return ansService.findAllByDetails(authorization);
	}
	
	@GetMapping("/findAllNative")
	public List findAllNative(@RequestHeader String authorization) throws Exception {
		return ansService.findAllNative(authorization);
	}
	
	@GetMapping("/findAllStudents")
	public List<StudentResponseTopFail> findAll(@RequestHeader String authorization) throws Exception {
		return  ansService.findAllStudents(authorization);
	}
	
	@GetMapping("/findTopStudents")
	public List<StudentResponseTopFail> findTop(@RequestParam int numberOfStudents,@RequestParam int successDegree,@RequestHeader String authorization) throws Exception {
		return  ansService.findTopStudents(numberOfStudents,successDegree,authorization);
	}
	
	@GetMapping("/findFailStudents")
	public List<StudentResponseTopFail> findFail(@RequestParam int successDegree,@RequestHeader String authorization) throws Exception {
		return  ansService.findFailStudents(successDegree,authorization);
	}
	
	
	
	
}
