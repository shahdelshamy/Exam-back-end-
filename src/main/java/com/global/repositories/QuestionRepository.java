package com.global.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.entities.QuestionIntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionIntity, Integer> {

	@Query("select q.correctAnswer from QuestionIntity q where q.id=:id ")
	public String findcorrectAnswerById(@Param("id")  int id);
	
	@Query("select count(*) from QuestionIntity")
	public int numberOfQuestions();
	
	
}
