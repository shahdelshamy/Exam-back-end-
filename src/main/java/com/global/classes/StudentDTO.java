package com.global.classes;

public class StudentDTO {
	
	int id;
	String name;
	String phone;
	float age;
	String email;
	String password;
	String year;
	

	public StudentDTO(int id, String name, String phone, float age, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.email = email;
		this.password = password;
	}
	
	public StudentDTO(int id, String name, String phone, float age, String email, String password, String year) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.email = email;
		this.password = password;
		this.year = year;
	}



	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
	
}
