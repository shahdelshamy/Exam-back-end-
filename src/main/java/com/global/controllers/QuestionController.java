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

import com.global.classes.QuestionDTO;
import com.global.entities.QuestionIntity;
import com.global.entities.StudentIntity;
import com.global.services.QuestionService;

@RestController
@RequestMapping("/question")    //false mean teacher
public class QuestionController {

	@Autowired
	QuestionService qService;
	
	@PostMapping("/insert")
	public String insert(@RequestBody QuestionDTO q,@RequestHeader String authentication) throws Exception {
		qService.insertQuestion(q,authentication);
		return "The question is added";
	}
	

	@PutMapping("/update")
	public String update(@RequestBody QuestionDTO q,@RequestHeader String authentication) throws Exception {
		qService.update(q,authentication);
		return "Question updated";
	}
	
	@GetMapping("/findAll")
	public List<QuestionDTO> findAll(@RequestHeader String authentication) throws Exception {
		return qService.findAll(authentication);
	}
	
	@GetMapping("/find/{id}")
	public QuestionIntity findById(@PathVariable int id,@RequestHeader String authentication) throws Exception {
		return qService.findById(id,authentication);
	}
	
	@GetMapping("/findCorrectAnswer/{id}")
	public String findAnswer(@PathVariable int id) {
		return qService.findCorrectAnswerById(id);
	}
	
	@GetMapping("/numberOfQuestions")
	public String numberOfQuestions(@RequestHeader String authentication) throws Exception  {
		return "The number of questions now is "+qService.countOfQuestions(authentication);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id,@RequestHeader String authentication) throws Exception {
		qService.delete(id,authentication);
		return "The question deleted";
	}
	
	
}
