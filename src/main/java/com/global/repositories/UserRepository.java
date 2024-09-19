package com.global.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.classes.UserResponse;
import com.global.entities.UserIntity;

@Repository
public interface UserRepository extends CrudRepository<UserIntity,Integer>{

	
	
	@Query("select new com.global.classes.UserResponse(user.token,user.id) from UserIntity user where user.id=:id")
	public UserResponse getToken(int id);
	
	
	@Query("select user.isStudent from UserIntity user where user.token=:authorization")
	public boolean checkAuthorization(String authorization);   //true if he stuent
	
}
