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
	
	
		//PJQL
		// must create object from StudentResponse beacause all response here from type AnswerIntity 
		//if we remove this com.global.classes.StudentResponse it give me error that it can't convert from AnswerIntity to StudentResponse
		//create and return custom object form StudentResponse 
		
	@Query("select new com.global.classes.StudentResponse(st.name,q.question,ans.answer,ans.score) from AnswerIntity ans join ans.student st join ans.question q order by st.name")
	public List<StudentResponse> findAllByDetails();
	
	     //SQL
	@Query(nativeQuery = true, value ="select st.name,q.question,ans.answer,ans.score from exam.answers ans join exam.users st,exam.questions q where ans.question_id=q.id and ans.student_id=st.id order by st.name")
	public List findAllStudentsNative();
	
	
	@Query("select new com.global.classes.StudentResponseTopFail(st.name,sum(ans.score) as degree) from AnswerIntity ans join ans.student st  group by st.name order by sum(ans.score) desc ")
	public List<StudentResponseTopFail> findAllStudents();
	
	
	
	@Query("select new com.global.classes.StudentResponseTopFail(st.name,sum(ans.score) as degree) from AnswerIntity ans join ans.student st  group by st.name having sum(ans.score) >= :successDegree order by sum(ans.score) desc ")
	public Page<StudentResponseTopFail> findTopStudents(Pageable pageable,int successDegree);
	
	@Query("select new com.global.classes.StudentResponseTopFail(st.name,sum(ans.score) as degree) from AnswerIntity ans join ans.student st  group by st.name having sum(ans.score) < :successDegree order by sum(ans.score) desc ")
	public List<StudentResponseTopFail> findFailStudents(int successDegree);
	
	
	
}
