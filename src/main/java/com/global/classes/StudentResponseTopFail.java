package com.global.classes;

public class StudentResponseTopFail {

	String name;
	Long degree;   //beacause the return value from aggregate function sum is long  
	
	
	public StudentResponseTopFail(String name, Long degree) {
		super();
		this.name = name;
		this.degree = degree;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDegree() {
		return degree;
	}
	public void setDegree(Long degree) {
		this.degree = degree;
	}
	
	
	
	
	
	
	
	
	
}
