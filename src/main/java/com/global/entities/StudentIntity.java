package com.global.entities;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Students")
public class StudentIntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NotNull(message = "Name Field Is Required")
	String name;
	@NotNull(message = "Phone Field Is Required")
	String phone;
	@NotNull(message = "Age Field Is Required")
	float age;
	
	@NotNull(message = "Email Field Is Required")
	String email;
	@NotNull(message = "Password Field Is Required")
	String password;
	
	@NotNull(message = "Teacher Field Is Required")
	@ManyToMany()
	@JoinTable(name = "student_teacher",joinColumns = @JoinColumn(name="student_id"),inverseJoinColumns = @JoinColumn(name="teacher_id"))
	List <TeacherIntity> teachers= new ArrayList<>();

	

	public List<TeacherIntity> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherIntity> teachers) {
		this.teachers = teachers;
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
