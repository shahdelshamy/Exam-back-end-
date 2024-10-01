package com.global.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Students")
public class StudentIntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String phone;
	float age;
	String email;
	String password;
	String teacherName;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public void setId(int id) {
		this.id=id;
	}
	
	public void setAge(float age) {
		this.age=age;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	
	public int getId() {
		return id;
	}
	
	public float getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	
	
	
	
	
}
