package com.global.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.entities.QuestionIntity;
import com.global.entities.UserIntity;
import com.global.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository qRepo;
	
	@Autowired
	UserService userService;
	
	public void insertQuestion(QuestionIntity question,String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			qRepo.save(question);
		}
		else {
			throw new Exception("The authorization not valid");
		}
	}
	
	
	public void update(QuestionIntity question,String authorization) throws Exception {
		if(qRepo.findById(question.getId()) !=null){
			this.insertQuestion(question, authorization);
		}
	}
	
	public List<QuestionIntity> findAll(String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			return (List<QuestionIntity>) qRepo.findAll();
		}else {
			throw new Exception("The authorization not valid");
		}
		
	}
	
	public QuestionIntity findById(int id) {
			return qRepo.findById(id).orElseThrow(()-> new RuntimeException("the question not found"));
		
	}
	
	public String findCorrectAnswerById(int id)  {
			return qRepo.findcorrectAnswerById(id);

	}
	
	public int countOfQuestions() {
			return qRepo.numberOfQuestions();
		
	}
	
	
	
	public void delete(int id,String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			qRepo.deleteById(id);
		}else {
			throw new Exception("The authorization not valid");
		}
		
	}
	
	
}
