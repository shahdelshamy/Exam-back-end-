package com.global.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "answers")
public class AnswerIntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	StudentIntity student;
	
	@NotNull(message = "Quesion Id Field is Required")
	@ManyToOne
	@JoinColumn(name = "question_id")
	QuestionIntity question;
	
	@NotNull(message = "Answer Id Field is Required")
	String answer;

	int score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

	public StudentIntity getStudent() {
		return student;
	}

	public void setStudent(StudentIntity student) {
		this.student = student;
	}

	public QuestionIntity getQuestion() {
		return question;
	}

	public void setQuestion(QuestionIntity question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	
	
	
	
	
}
