package com.global.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.classes.QuestionDTO;
import com.global.classes.QuestionDTOAsStudent;
import com.global.entities.QuestionIntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionIntity, Integer> {

	@Query("select q.correctAnswer from QuestionIntity q where q.id=:id ")
	public String findcorrectAnswerById(@Param("id")  int id);
	
	@Modifying
	@Query("update QuestionIntity qTable set qTable.choice1=:#{#q.choice1},qTable.choice2=:#{#q.choice2},qTable.choice3=:#{#q.choice3},qTable.choice4=:#{#q.choice4},qTable.correctAnswer=:#{#q.correctAnswer},qTable.question=:#{#q.question} where qTable.id=:#{#q.id}  ")
	public int update(QuestionDTO q);
	
	@Query("select new com.global.classes.QuestionDTO(q.id,q.question,q.choice1,q.choice2,q.choice3,q.choice4,q.correctAnswer) from QuestionIntity q where q.teacher.name=:name")
	public List<QuestionDTO> findAllQuestions(String name);
	
	@Query("select count(*) from QuestionIntity q where q.teacher.name=:name")
	public int numberOfQuestions(String name);
	
	@Query("select new com.global.classes.QuestionDTOAsStudent(q.id,q.question,q.choice1,q.choice2,q.choice3,q.choice4) from QuestionIntity q where q.teacher.name=:teacherName ")
	public List<QuestionDTOAsStudent>findQuestionsAsStudent(String teacherName) ;
	
	@Query("select q from QuestionIntity q join q.teacher t where t.name=:name and q.id=:id")
	public Optional<QuestionIntity> findByIdAndName(int id,String name);

	
}
