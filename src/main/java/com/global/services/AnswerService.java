package com.global.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.global.classes.AnswerDTO;
import com.global.classes.Authentication;
import com.global.classes.QuestionDTOAsStudent;
import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.classes.TeacherDTO;
import com.global.classes.StudentDTO;
import com.global.entities.AnswerIntity;
import com.global.entities.QuestionIntity;
import com.global.entities.StudentIntity;
import com.global.entities.TeacherIntity;
import com.global.repositories.AnswerRepository;


@Service
public class AnswerService {

	@Autowired
	AnswerRepository ansRepo;
	
	@Autowired
	QuestionService qService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TeacherService teacherService;
	
	private Authentication token=new Authentication();
	
	
	public void insert(AnswerDTO answer,String authentication,String teacherName) throws Exception {
		
		StudentDTO user=new StudentDTO();
		boolean checkTeacher;
		
		if(token.decriptStudent(authentication) instanceof StudentIntity) {
			 StudentIntity studentFromToken=token.decriptStudent(authentication);
			 for(int i=0;i<studentFromToken.getTeachers().size();i++) {
				 if(studentFromToken.getTeachers().get(i).getName().equalsIgnoreCase(teacherName)) {
					 
					Optional<AnswerIntity> ansExist= ansRepo.findByStudentIdAndQuestionId(answer.getStudentId(), answer.getQuestionId())	;
						
					StudentIntity student=studentService.findById(studentFromToken.getId());
						
					QuestionIntity question=qService.checkingQuestion(answer.getQuestionId(),teacherName);
						
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
					}
					 
				 }
			 }else {
				throw new Exception("Not Allowed");
			}

	}
	
	
	public void update(AnswerDTO answer,String authentication,String teacherName) throws Exception {
			this.insert(answer,authentication,teacherName);
	}
	

	public List<QuestionDTOAsStudent>findQuestionsAsStudent(String authentication,String teacherName) throws Exception {
		boolean checkTeacher = false;
		if(token.decriptStudent(authentication) instanceof StudentIntity ) {
			StudentIntity student=token.decriptStudent(authentication);
			for(int i=0; i<student.getTeachers().size();i++) {
				if(student.getTeachers().get(i).getName().equalsIgnoreCase(teacherName)) {
					checkTeacher=true;
					return qService.findQuestionsAsStudent(teacherName);
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
	
	public List<TeacherDTO> findTeachers(String authentication) throws Exception{
		if(token.decriptStudent(authentication) instanceof StudentIntity) {
			return teacherService.findTeacherAsStudent(token.decriptStudent(authentication).getId());
		}else {
			throw new Exception("Not Allowed");
		}
	}
	
	
	          //Teacher
	public List<StudentResponse> findAllByDetails(String authentication) throws Exception {
		if(token.decriptTeacher(authentication) instanceof TeacherIntity ) {
			TeacherIntity teacher=token.decriptTeacher(authentication);
			return (List<StudentResponse>) ansRepo.findAllByDetails(teacher.getName());
		}else {
			throw new Exception("Not Allowed");
		}
		
	}
	
	public List<StudentResponseTopFail> findAllStudents(String authentication) throws Exception {
		if(token.decriptTeacher(authentication) instanceof TeacherIntity) {
			TeacherIntity teacher=token.decriptTeacher(authentication);
			return ansRepo.findAllStudents(teacher.getName());   //getContent to return list
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
	}
	
	public List<StudentResponseTopFail> findTopStudents(int limit,int successDegree,String authentication) throws Exception {
		if(token.decriptTeacher(authentication) instanceof TeacherIntity) {
			
			TeacherIntity teacher=token.decriptTeacher(authentication);
			
			Pageable pageable=PageRequest.of(0, limit);
			return ansRepo.findTopStudents(pageable,successDegree,teacher.getName()).getContent();   //getContent to return list
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
		
	}
	
	public List<StudentResponseTopFail> findFailStudents(int successDegree,String authentication) throws Exception {
		if(token.decriptTeacher(authentication) instanceof TeacherIntity) {
			
			TeacherIntity teacher=token.decriptTeacher(authentication);
			
			return ansRepo.findFailStudents(successDegree,teacher.getName());  
		}else {
			throw new Exception("the authoriation token not valid");
		}
		
	}
	
	
}

