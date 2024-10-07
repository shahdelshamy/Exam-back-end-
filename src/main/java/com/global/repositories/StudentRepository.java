package com.global.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.classes.StudentDTO;
import com.global.classes.UserResponse;
import com.global.entities.StudentIntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentIntity,Integer>{

	
	
//	@Query("select new com.global.classes.UserResponse(user.token,user.id) from UserIntity user where user.id=:id")
//	public UserResponse getToken(int id);
	
	@Query("select student.id from StudentIntity student where student.email=:email and student.password=:password")
	public Integer selectId(String email,String password);
	
	@Query("select new com.global.classes.StudentDTO(s.id,s.name,s.phone,s.age,s.email,s.password,y.year) from StudentIntity s join s.teachers t left join s.year y where t.id=:id")
	public List<StudentDTO> findByTeacherName(int id);
	
	@Query("select new com.global.classes.StudentDTO(s.id,s.name,s.phone,s.age,s.email,s.password,y.year) from StudentIntity s join s.teachers t join s.year y  where t.id=:id and y.year=:year ")
	public List<StudentDTO> findByTeacherNameAndYear(int id,String year);
	
	
//	@Query("select user.isStudent from UserIntity user where user.token=:authorization")
//	public boolean checkAuthorization(String authorization);   //true if he stuent
	
}
