package com.global.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.entities.AnswerIntity;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerIntity, Integer> {
	
	public Optional<AnswerIntity> findByStudentIdAndQuestionId(int studentId, int questionId);
	
	
	
	
}
