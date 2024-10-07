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
		boolean checkTeacher = false;
		
		if(token.decriptStudent(authentication) instanceof StudentIntity) {
			 StudentIntity studentFromToken=token.decriptStudent(authentication);
			 for(int i=0;i<studentFromToken.getTeachers().size();i++) {
				 if(studentFromToken.getTeachers().get(i).getName().equals(teacherName)) {
					 
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
							checkTeacher=true;
					}
					 
				 }
			 if(checkTeacher == false ) {
				 throw new Exception("You not take in this teacher");
			 }
			 }else {
				throw new Exception("Not Allowed");
			}

	}
	
	
	public void update(AnswerDTO answer,String authentication,String teacherName) throws Exception {
			this.insert(answer,authentication,teacherName);
	}
	
	
	
	
}

