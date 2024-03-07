package com.service;

import java.util.List;
import com.model.Batch;

public interface BatchService 
{
	Batch addBatch(Batch batch);

	public Batch getOneBatch(int id);

	public List<Batch> getAllBatch();
	
	public Batch updateBatch(Batch s);

	public List<Batch> deleteBatch(int id);

	public List<Batch> findByName(String bname);
}
