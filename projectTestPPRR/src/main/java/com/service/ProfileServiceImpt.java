package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProfileRepository;
import com.model.Profile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class ProfileServiceImpt implements ProfileService
{
	@Autowired
	ProfileRepository profileRepo;

	@Autowired
    EntityManager entityManager; 
	
	public Profile addProfile(Profile p) 
	{
		return profileRepo.save(p);
	}
	
	public Profile getOneProfile(int id)
	{
		return profileRepo.findById(id).orElse(null);
	}
	
	public List<Profile> getAllProfile()
	{
		return profileRepo.findAll();
	}
	
	public Profile updateProfile(Profile p)
	{
		Profile existingProfile = profileRepo.findById(p.getId()).orElse(null);
		 // Check if the existing subtopic exists
	    if(existingProfile != null) 
	    {
	        // Update the name of the existing subtopic
	        existingProfile.setFirstName(p.getFirstName());
	        
	        // Save the existingSubTopic entity
	        return profileRepo.save(existingProfile);
	    } 
	    else 
	    {
	        // Handle the case where the existing subtopic is not found
	        return null; // or throw an exception, depending on requirements
	    }
	}
	
	@Transactional //
	public List<Profile> deleteProfile(int id)
	{
		// Deleting related records from the batch_trainer table using native SQL query
/*        Query query = entityManager.createNativeQuery("DELETE FROM batch_trainer WHERE  trainer_id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
*/		
		Profile profile = profileRepo.findById(id).orElse(null);
        if (profile != null) {
         
        	profileRepo.delete(profile);
        }

        return profileRepo.findAll();
        
	}

	public List<Profile> find(String email) 
	{
		return profileRepo.find(email);
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
	
	  /*	public List<Profile> findByName(String name) {
      "JOIN batch_trainer bt ON t.id = bt.trainer_id " +
                "JOIN batch b ON bt.batch_id = b.bid " +
                "WHERE t.name = :name", Trainer.class);
                query.setParameter("name", name);
                return query.getResultList();
	    return profileRepo.findByName(name);
	}
	*/
	
}
