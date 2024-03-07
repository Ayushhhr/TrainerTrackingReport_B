package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.TrainerSubTopicAssociation;

public interface TsaRepository extends JpaRepository<TrainerSubTopicAssociation,Integer>
{
	
}
