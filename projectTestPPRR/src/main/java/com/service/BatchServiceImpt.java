package com.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.BatchRepository;
import com.dao.TrainerRepository;
import com.model.Batch;
import com.model.Trainer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class BatchServiceImpt implements BatchService{

	@Autowired
	BatchRepository batchRepo;
	

    @Autowired
    TrainerRepository trainerRepository;
	
	@Autowired
    EntityManager entityManager; 
	
	@Transactional // Adding this annotation to ensure that the method runs within a transaction
	public Batch addBatch(Batch b) 
	{
		
		// Save the batch
        Batch savedBatch = batchRepo.save(b);
        
        // Fetch all trainers associated with the added batch
        List<Trainer> trainers = savedBatch.getTrainers();
        for (Trainer trainer : trainers) {
            // Update the trainer_batch table
            updateTrainerBatch(trainer.getId(), savedBatch.getBid());
        }
        
        return savedBatch;
		/*
        // Fetch all trainers associated with the added batch
        List<Trainer> trainers = savedBatch.getTrainers();
        for (Trainer trainer : trainers) {
            // Update the batches column in the Trainer table
            updateTrainerBatches(trainer.getId());
        }
        return savedBatch;
		
		//return batchRepo.save(b); */
	}
	
	// Method to update the trainer_batch table
    private void updateTrainerBatch(int trainerId, int batchId) {
        // Create a native query to insert a new record into trainer_batch table
        Query query = entityManager.createNativeQuery("INSERT INTO trainer_batch (trainer_id, batch_bid) VALUES (:trainerId, :batchId)");
        query.setParameter("trainerId", trainerId);
        query.setParameter("batchId", batchId);
        query.executeUpdate();
    }
	
/*	// Method to update the batches column in the Trainer table
    private void updateTrainerBatches(int trainerId)
    {
        // Creating a native query to update the batches column
        Query query = entityManager.createNativeQuery("UPDATE Trainer SET batches = " +
                "(SELECT GROUP_CONCAT(bname SEPARATOR ', ') FROM Batch WHERE bid IN " +
                "(SELECT batch_id FROM batch_trainer WHERE trainer_id = :trainerId)) " +
                "WHERE id = :trainerId");
        query.setParameter("trainerId", trainerId);
        query.executeUpdate();
    } */
	
	public Batch getOneBatch(int id)
	{
		return batchRepo.findById(id).orElse(null);
	}
	
	public List<Batch> getAllBatch()
	{
		return batchRepo.findAll();
	}
	
	public List<Batch> findByName(String bname)
	{
		return batchRepo.findByName(bname);
	}
	
	public Batch updateBatch(Batch s)
	{
		Batch existingBatch = batchRepo.findById(s.getBid()).orElse(null);
		 // Checking if the existing subtopic exists
	    if(existingBatch != null) 
	    {
	        // Updating the name of the existing subtopic
	        existingBatch.setBname(s.getBname());
	        
	        // Save the existingSubTopic entity
	        return batchRepo.save(existingBatch);
	    } 
	    else 
	    {
	        // Handle the case where the existing subtopic is not found
	        return null; // or throw an exception, depending on requirements
	    }
	}
	
	@Transactional //
	public List<Batch> deleteBatch(int id)
	{
		// Deleting related records from the trainer_batch table using native SQL query
        Query query = entityManager.createNativeQuery("DELETE FROM trainer_batch WHERE batch_bid = :id");
        query.setParameter("id", id);
        query.executeUpdate();
	 	
        Batch Batch = batchRepo.findById(id).orElse(null);
        if (Batch != null) {
         
            batchRepo.delete(Batch);
        }

        return batchRepo.findAll();
        
       /* 
		subtopicRepo.deleteById(id); 
		return subtopicRepo.findAll();
	  */
	}

}
/* @Transactional
 * The error message (TransactionRequiredException) suggests that the operation requires an active transaction,
 *  but none is present. This typically happens when trying to execute an update or delete query outside the scope of a transaction. 
*/