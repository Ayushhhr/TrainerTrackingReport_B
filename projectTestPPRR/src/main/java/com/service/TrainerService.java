package com.service;

import java.util.List;

import com.model.Batch;
import com.model.Trainer;

public interface TrainerService 
{
	Trainer addTrainer(Trainer trainer);
	
	public Trainer getOneTrainer(int id);
	
	public Trainer updateTrainer(Trainer s);

	public List<Trainer> deleteTrainer(int id);

	public List<Trainer> getAllTrainers();
	
	public List<Trainer> find(String email);
	
	public List<Trainer> findByName(String name);
	
	
}
