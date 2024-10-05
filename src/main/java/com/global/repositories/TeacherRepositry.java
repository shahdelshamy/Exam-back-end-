package com.global.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.classes.TeacherDTO;
import com.global.entities.TeacherIntity;

@Repository
public interface TeacherRepositry extends CrudRepository<TeacherIntity, Integer>{

	@Query("select teacher.id from TeacherIntity teacher where teacher.email=:email and teacher.password=:password")
	public int selectId(String email,String password); 
	
	@Query("select new com.global.classes.TeacherDTO(t.id,t.name,t.age,t.phone,t.matrial) from StudentIntity s join s.teachers t where s.id=:id ")
	public List<TeacherDTO>findTeacherAsStudent(int id);
}
