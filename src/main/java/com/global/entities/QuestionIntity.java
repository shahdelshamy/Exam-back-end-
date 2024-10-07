package com.global.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "questions")
public class QuestionIntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NotNull(message = "Name Field is Required")
	String question;
	@NotNull(message = "Choice1 Field is Required")
	String choice1;
	@NotNull(message = "Choice2 Field is Required")
	String choice2;
	String choice3;
	String choice4;
	@NotNull(message = "Correct Answer Field is Required")
	String correctAnswer;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
    TeacherIntity teacher;
	
	@ManyToOne
	@JoinColumn(name = "year_id")
    YearIntity year;
	

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

	public YearIntity getYear() {
		return year;
	}

	public void setYear(YearIntity year) {
		this.year = year;
	}

	public TeacherIntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherIntity teacher) {
		this.teacher = teacher;
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


