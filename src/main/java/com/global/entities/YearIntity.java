package com.global.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "years")
public class YearIntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String year;
	
	@ManyToMany(mappedBy = "years")
	@JsonIgnore
	List<TeacherIntity>teachers;
	
	@OneToMany(mappedBy = "year")
	@JsonIgnore
	List<StudentIntity>students;


	public List<StudentIntity> getStudents() {
		return students;
	}
	public void setStudents(List<StudentIntity> students) {
		this.students = students;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<TeacherIntity> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<TeacherIntity> teachers) {
		this.teachers = teachers;
	}
	
	
	
	
}
