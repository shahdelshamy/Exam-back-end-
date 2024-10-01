package com.global.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.classes.Authentication;
import com.global.classes.QuestionDTO;
import com.global.classes.QuestionDTOAsStudent;
import com.global.classes.StudentDTO;
import com.global.entities.QuestionIntity;
import com.global.entities.StudentIntity;
import com.global.entities.TeacherIntity;
import com.global.repositories.QuestionRepository;
import com.google.gson.Gson;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuestionService {

	@Autowired
	QuestionRepository qRepo;
	
	@Autowired
	StudentService studentService;
	
	private Authentication token=new Authentication();
	
	
	
	public void insertQuestion(QuestionDTO question,String authorization) throws Exception {
		
		if(token.decriptTeacher(authorization) instanceof TeacherIntity ) {
			
		    TeacherIntity teacher= token.decriptTeacher(authorization);
		    
		    QuestionIntity q=new QuestionIntity();
		    
			q.setQuestion(question.getQuestion());
			q.setChoice1(question.getChoice1());
			q.setChoice2(question.getChoice2());
			q.setChoice3(question.getChoice3());
			q.setChoice4(question.getChoice4());
			q.setCorrectAnswer(question.getCorrectAnswer());
			q.setMatrial(teacher.getMatrial());
			q.setTeacherName(teacher.getName());
			
			qRepo.save(q);
		}else {
			throw new Exception("Not Allowed");
		}		
		
	}
	
	
	public void update(QuestionDTO question,String authorization) throws Exception {
		
		if(token.decriptTeacher(authorization) instanceof TeacherIntity) {
			if(this.findById(question.getId(),authorization) !=null){
				qRepo.update(question);
			}else {
				throw new Exception("The Question not found");
			}
		}else {
			throw new Exception("Not Allowed");
		}		
	}
	
	public QuestionIntity findById(int id,String authorization) throws Exception {
		if(token.decriptTeacher(authorization) instanceof TeacherIntity) {
			return qRepo.findById(id).orElseThrow(()-> new RuntimeException("the question not found"));

		}else {
			throw new Exception("Not Allowed");
		}
	}
	
	public QuestionIntity checkingQuestion(int id) throws Exception {
			return qRepo.findById(id).orElseThrow(()-> new RuntimeException("the question not found"));
	}
	
	public List<QuestionDTO> findAll(String authorization) throws Exception {
		if(token.decriptTeacher(authorization) instanceof TeacherIntity) {
			TeacherIntity teacher=token.decriptTeacher(authorization);
			return (List<QuestionDTO>) qRepo.findAllQuestions(teacher.getName());
		}else {
			throw new Exception("Not Allowed");
		}
		
	}
	
	public List<QuestionDTOAsStudent>findQuestionsAsStudent(String teacherName) {
		return qRepo.findQuestionsAsStudent(teacherName);
	}

	
	public int countOfQuestions(String authorization) throws Exception {
		if(token.decriptTeacher(authorization) instanceof TeacherIntity) {
			TeacherIntity teacher=token.decriptTeacher(authorization);
			return qRepo.numberOfQuestions(teacher.getName());
		}else {
			throw new Exception("Not Allowed");
		}
			
		
	}
	
	public String findCorrectAnswerById(int id)  {
			return qRepo.findcorrectAnswerById(id);
	}

	
	public void delete(int id,String authorization) throws Exception {
		if(token.decriptTeacher(authorization) instanceof TeacherIntity) {
			qRepo.deleteById(id);
		}else {
			throw new Exception("Not Allowed");
		}
		
	}
	
	

}

