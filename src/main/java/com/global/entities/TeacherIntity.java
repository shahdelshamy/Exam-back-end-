package com.global.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "teachers")
public class TeacherIntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NotNull(message = "Name Field is Required")
	String name;
	@NotNull(message = "Phone Field is Required")
	String phone;
	@NotNull(message = "Age Field is Required")
	float age;
	@NotNull(message = "Email Field is Required")
	String email;
	@NotNull(message = "Password Field is Required")
	String password;
	@NotNull(message = "Matrial Field is Required")
	String matrial;
	@NotNull(message = "University Field is Required")
	String university;
	@NotNull(message = "Year Field is Required")
	
	@ManyToMany()
	@JoinTable(name = "years_teachers",joinColumns = @JoinColumn(name="teacher_id"),inverseJoinColumns = @JoinColumn(name="year_id"))
	List<YearIntity> years;
	
	@ManyToMany(mappedBy = "teachers")
	@JsonIgnore
	List<StudentIntity>students;
	
	
	public List<YearIntity> getYears() {
		return years;
	}
	public void setYears(List<YearIntity> years) {
		this.years = years;
	}
	public List<StudentIntity> getStudents() {
		return students;
	}
	public void setStudents(List<StudentIntity> students) {
		this.students = students;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMatrial() {
		return matrial;
	}
	public void setMatrial(String matrial) {
		this.matrial = matrial;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	

}
