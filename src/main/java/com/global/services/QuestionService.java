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
import com.global.entities.YearIntity;
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
	
	@Autowired
	TeacherService teacherService;
	
	private Authentication token=new Authentication();
	
	
	
	public void insertQuestion(QuestionDTO question,String authorization,String year) throws Exception {
		
		if(token.decriptTeacher(authorization) instanceof TeacherIntity ) {
			
		    TeacherIntity teacher= token.decriptTeacher(authorization);
		    
		   List<YearIntity> yearList= teacherService.findYearsById(teacher.getId());
		    
		   for(int i=0;i<yearList.size();i++) {
			   if(yearList.get(i).getYear().equals(year)) {
				   QuestionIntity q=new QuestionIntity();
				    
					q.setQuestion(question.getQuestion());
					q.setChoice1(question.getChoice1());
					q.setChoice2(question.getChoice2());
					q.setChoice3(question.getChoice3());
					q.setChoice4(question.getChoice4());
					q.setCorrectAnswer(question.getCorrectAnswer());
					q.setTeacher(teacher);
					q.setYear(yearList.get(i));
					qRepo.save(q);
			   }else {
				   new RuntimeException("You Not Take This Year");
			   }
		    }	
			
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
	
	public QuestionIntity checkingQuestion(int id,String TeacherName) throws Exception {
			return qRepo.findByIdAndName(id,TeacherName).orElseThrow(()->new RuntimeException("the question not found"));
	}
	
	public List<QuestionDTO> findAll(String authorization) throws Exception {
		if(token.decriptTeacher(authorization) instanceof TeacherIntity) {
			TeacherIntity teacher=token.decriptTeacher(authorization);
			return (List<QuestionDTO>) qRepo.findAllQuestions(teacher.getName());
		}else {
			throw new Exception("Not Allowed");
		}
		
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

	public List<QuestionDTOAsStudent>findQuestionsAsStudent(String authentication,String teacherName) throws Exception {
		boolean checkTeacher = false;
		if(token.decriptStudent(authentication) instanceof StudentIntity ) {
			StudentIntity student=token.decriptStudent(authentication);
			for(int i=0; i<student.getTeachers().size();i++) {
				if(student.getTeachers().get(i).getName().equalsIgnoreCase(teacherName)) {
					checkTeacher=true;
					return qRepo.findQuestionsAsStudent(teacherName);
				}
			}
			if(checkTeacher==false) {
				throw new Exception("You Not Take "+teacherName+" 's teacher");
			}
			
		}else {
			throw new Exception("Not Allowed");
		}
		return null;
	}
	
	public void delete(int id,String authorization) throws Exception {
		if(token.decriptTeacher(authorization) instanceof TeacherIntity) {
			qRepo.deleteById(id);
		}else {
			throw new Exception("Not Allowed");
		}
		
	}
	
	

}

