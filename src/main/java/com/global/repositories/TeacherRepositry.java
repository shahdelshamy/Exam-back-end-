package com.global.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.classes.StudentResponse;
import com.global.classes.StudentResponseTopFail;
import com.global.classes.TeacherDTO;
import com.global.entities.TeacherIntity;
import com.global.entities.YearIntity;

@Repository
public interface TeacherRepositry extends CrudRepository<TeacherIntity, Integer>{

	@Query("select teacher.id from TeacherIntity teacher where teacher.email=:email and teacher.password=:password")
	public int selectId(String email,String password); 
	
	@Query("select new com.global.classes.TeacherDTO(t.id,t.name,t.age,t.phone,t.matrial) from StudentIntity s join s.teachers t where s.id=:id ")
	public List<TeacherDTO>findTeacherAsStudent(int id);
	
	@Query("select y from TeacherIntity t join t.years y where t.id=:id")
	public List<YearIntity> findYearsById(int id);
	
	
	//Teacher
		
		//PJQL
		// must create object from StudentResponse beacause all response here from type AnswerIntity 
		//if we remove this com.global.classes.StudentResponse it give me error that it can't convert from AnswerIntity to StudentResponse
		//create and return custom object form StudentResponse 
		
	@Query("select new com.global.classes.StudentResponse(st.name,q.question,ans.answer,ans.score) from AnswerIntity ans join ans.student st join ans.question q join st.year y where q.teacher.name=:teacherName and y.year=:year order by st.name")
	public List<StudentResponse> findAllByDetails(String teacherName,String year);
	

	     //SQL
	@Query(nativeQuery = true, value ="select st.name,q.question,ans.answer,ans.score from exam.answers ans join exam.users st,exam.questions q where ans.question_id=q.id and ans.student_id=st.id order by st.name")
	public List findAllStudentsNative();
	
	
	@Query("select new com.global.classes.StudentResponseTopFail(st.name,sum(ans.score) as degree) from AnswerIntity ans join ans.student st  join ans.question.teacher t  where t.id=:id group by st.name  order by sum(ans.score) desc ")
	public List<StudentResponseTopFail> findAllReasultOfStudents(int id);
	
	@Query("select new com.global.classes.StudentResponseTopFail(st.name,sum(ans.score) as degree) from AnswerIntity ans join ans.student st  join ans.question.teacher t join st.year y  where t.id=:id and y.year=:year group by st.name  order by sum(ans.score) desc ")
	public List<StudentResponseTopFail> findResultsByYear(int id,String year);
	
	@Query("select new com.global.classes.StudentResponseTopFail(st.name,sum(ans.score) as degree) from AnswerIntity ans join ans.student st join ans.question.teacher t join st.year y  where t.name=:teacherName and y.year=:year group by st.name having sum(ans.score) >= :successDegree order by sum(ans.score) desc ")
	public Page<StudentResponseTopFail> findTopStudents(Pageable pageable,int successDegree,String teacherName,String year);
	
	@Query("select new com.global.classes.StudentResponseTopFail(st.name,sum(ans.score) as degree) from AnswerIntity ans join ans.student st join ans.question.teacher t join st.year y where t.name=:teacherName and y.year=:year group by st.name having sum(ans.score) < :successDegree order by sum(ans.score) desc ")
	public List<StudentResponseTopFail> findFailStudents(int successDegree,String teacherName,String year);
	

	
	
	
}
