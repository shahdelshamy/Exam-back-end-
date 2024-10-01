package com.global.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.entities.TeacherIntity;

@Repository
public interface TeacherRepositry extends CrudRepository<TeacherIntity, Integer>{

	@Query("select teacher.id from TeacherIntity teacher where teacher.email=:email and teacher.password=:password")
	public int selectId(String email,String password); 
}
