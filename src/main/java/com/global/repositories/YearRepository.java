package com.global.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.global.entities.YearIntity;

@Repository
public interface YearRepository extends CrudRepository<YearIntity, Integer> {

	@Query("select y from YearIntity y join y.teachers t where t.id=:id ")
	public List<YearIntity> findYear(int id);
	
}
