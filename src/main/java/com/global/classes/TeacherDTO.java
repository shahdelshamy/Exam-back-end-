package com.global.classes;

public class TeacherDTO {

	int id;
	float age;
	String phone;
	String name;
	String matrial;
	
	
	public TeacherDTO(int id, String name, float age, String phone, String matrial) {
		super();
		this.id = id;
		this.age = age;
		this.phone = phone;
		this.name = name;
		this.matrial = matrial;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMatrial() {
		return matrial;
	}
	public void setMatrial(String matrial) {
		this.matrial = matrial;
	}
	
	
	
}
