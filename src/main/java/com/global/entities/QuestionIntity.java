package com.global.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class QuestionIntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String question;
	String choice1;
	String choice2;
	String choice3;
	String choice4;
	String correctAnswer;
	
	String matrial;   //not required it will take it from token of teacher
	String teacherName;   
	
	
	public QuestionIntity(int id, String question, String choice1, String choice2, String choice3, String choice4) {
		this.id = id;
		this.question = question;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
	}
	
	public QuestionIntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getMatrial() {
		return matrial;
	}
	public void setMatrial(String matrial) {
		this.matrial = matrial;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getChoice1() {
		return choice1;
	}
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}
	public String getChoice2() {
		return choice2;
	}
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}
	public String getChoice3() {
		return choice3;
	}
	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}
	public String getChoice4() {
		return choice4;
	}
	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	
	
	
	
	
}


