package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BatchRepository;
import com.dao.TrainerRepository;
import com.model.Trainer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class TrainerServiceImpt implements TrainerService {

	@Autowired
	TrainerRepository trainerRepo;
	
	@Autowired
	BatchRepository batchRepo;
	
	@Autowired
    EntityManager entityManager; 
	
	@Override
	public Trainer addTrainer(Trainer t) 
	{
		return trainerRepo.save(t);
	}
	
	public Trainer getOneTrainer(int id)
	{
		return trainerRepo.findById(id).orElse(null);
	}
	
	public List<Trainer> getAllTrainers()
	{
		return trainerRepo.findAll();
	}
	
	public Trainer updateTrainer(Trainer s)
	{
		Trainer existingTrainer = trainerRepo.findById(s.getId()).orElse(null);
		 // Check if the existing subtopic exists
	    if(existingTrainer != null) 
	    {
	        // Update the name of the existing subtopic
	        existingTrainer.setName(s.getName());
	        
	        // Save the existingSubTopic entity
	        return trainerRepo.save(existingTrainer);
	    } 
	    else 
	    {
	        // Handle the case where the existing subtopic is not found
	        return null; // or throw an exception, depending on requirements
	    }
	}
	
	@Transactional //
	public List<Trainer> deleteTrainer(int id)
	{
		// Deleting related records from the batch_trainer table using native SQL query
        Query query = entityManager.createNativeQuery("DELETE FROM batch_trainer WHERE  trainer_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
		
        Trainer trainer = trainerRepo.findById(id).orElse(null);
        if (trainer != null) {
         
        	trainerRepo.delete(trainer);
        }

        return trainerRepo.findAll();
        
	}

	@Override
	public List<Trainer> find(String email) 
	{
		return trainerRepo.find(email);
	}
	
/*	
	@Overrides
	public List<Trainer> findByName(String name) 
	{
		//return trainerRepo.findByName(name);
		 List<Trainer> trainers = trainerRepo.findByName(name);
		// Fetch batches for each trainer
        for (Trainer trainer : trainers) {
            List<Batch> batches = batchRepo.findByTrainersId(trainer.getId());
            trainer.setBatch(batches);
        }
        return trainers;
	}
 */
	
	@Override
	@Transactional
	public List<Trainer> findByName(String name) {
   /*     "JOIN batch_trainer bt ON t.id = bt.trainer_id " +
                "JOIN batch b ON bt.batch_id = b.bid " +
                "WHERE t.name = :name", Trainer.class);
                query.setParameter("name", name);
                return query.getResultList();*/
	    return trainerRepo.findByName(name);
	}
	
	
}
