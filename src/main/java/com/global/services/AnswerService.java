package com.global.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.global.classes.AnswerDTO;
import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.entities.AnswerIntity;
import com.global.entities.QuestionIntity;
import com.global.entities.UserIntity;
import com.global.repositories.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	AnswerRepository ansRepo;
	
	
	@Autowired
	QuestionService qService;
	
	@Autowired
	UserService userService;
	
	
	public void insert(AnswerDTO answer,String authorization) throws Exception {
		
		if(userService.checkAuthorization(authorization)) {
			
			Optional<AnswerIntity> ansExist= ansRepo.findByStudentIdAndQuestionId(answer.getStudentId(), answer.getQuestionId())	;
			
			UserIntity student=userService.findById(answer.getStudentId());
			
			QuestionIntity question=qService.findById(answer.getQuestionId());
			
			AnswerIntity ansIntity;
			
			if(ansExist.isPresent()) {
				ansIntity=ansExist.get();
				ansIntity.setAnswer(answer.getAnswer());
			}else {
				ansIntity=new AnswerIntity();
				ansIntity.setStudent(student);
				ansIntity.setQuestion(question);
				
				ansIntity.setAnswer(answer.getAnswer());
			}
				
			String correctAnswer =qService.findCorrectAnswerById(question.getId());
			
			if(correctAnswer.equals(answer.getAnswer())) {
				ansIntity.setScore(1);
			}else {
				ansIntity.setScore(0);
			}
			
				ansRepo.save(ansIntity);
				
		}else {
			throw new Exception("the authoriation token not valid");
		}
	
		
	}
	
	
	public void update(AnswerDTO answer,String authorization) throws Exception {
			this.insert(answer,authorization);
	}
	

	public List<StudentResponse> findAllByDetails(String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			return (List<StudentResponse>) ansRepo.findAllByDetails();
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
	}
	
	public List findAllNative(String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			return (List) ansRepo.findAllStudentsNative();
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
	}
	
	
	public List<StudentResponseTopFail> findAllStudents(String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			return ansRepo.findAllStudents();   //getContent to return list
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
	}
	
	public List<StudentResponseTopFail> findTopStudents(int limit,int successDegree,String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			Pageable pageable=PageRequest.of(0, limit);
			return ansRepo.findTopStudents(pageable,successDegree).getContent();   //getContent to return list
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
		
	}
	
	public List<StudentResponseTopFail> findFailStudents(int successDegree,String authorization) throws Exception {
		if(!userService.checkAuthorization(authorization)) {
			return ansRepo.findFailStudents(successDegree);  
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
	}
	
	
}
