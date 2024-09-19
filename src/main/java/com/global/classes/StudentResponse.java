package com.global.classes;

public class StudentResponse {

	
	String studentName;
	String question;
	String answer;
	int score;
	
	
	
	public StudentResponse(String studentName, String question, String answer, int score) {
		super();
		this.studentName = studentName;
		this.question = question;
		this.answer = answer;
		this.score = score;
	}
	
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
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
