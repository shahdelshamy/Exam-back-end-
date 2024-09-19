package com.global.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.global.entities.QuestionIntity;
import com.global.entities.UserIntity;
import com.global.services.QuestionService;

@RestController
@RequestMapping("/question/false")    //false mean teacher
public class QuestionController {

	@Autowired
	QuestionService qService;
	
	@PostMapping("/insert")
	public String insert(@RequestBody QuestionIntity q,@RequestHeader String authorization) throws Exception {
		qService.insertQuestion(q,authorization);
		return "The question is added";
	}
	

	@PutMapping("/update")
	public String update(@RequestBody QuestionIntity q,@RequestHeader String authorization) throws Exception {
		qService.update(q,authorization);
		return "Question updated";
	}
	
	@GetMapping("/findAll")
	public List<QuestionIntity> findAll(@RequestHeader String authorization) throws Exception {
		return qService.findAll(authorization);
	}
	
	@GetMapping("/find/{id}")
	public QuestionIntity findById(@PathVariable int id) {
		return qService.findById(id);
	}
	
	@GetMapping("/findCorrectAnswer/{id}")
	public String findAnswer(@PathVariable int id) {
		return qService.findCorrectAnswerById(id);
	}
	
	@GetMapping("/numberOfQuestions")
	public String numberOfQuestions()  {
		return "The number of questions now is "+qService.countOfQuestions();
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id,@RequestHeader String authorization) throws Exception {
		qService.delete(id,authorization);
		return "The question deleted";
	}
	
	
}
