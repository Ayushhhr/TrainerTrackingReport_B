package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> 
{
	@Query("select t from Trainer t where t.email =:e")
	public List<Trainer> find(@Param("e")String email);
	
	public List<Trainer> findByName(String name);
	
}
