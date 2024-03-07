package com.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Trainer 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique=true)
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnore
	private List<Batch> batch;
	
	@JsonBackReference
	@OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TrainerSubTopicAssociation> trainerTopicAssociations;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Course> course;
/*
	Adding a new field to hold batch names
	@ElementCollection
    private List<String> batches;
*/	
/*	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Topic> topic;
*/	
	public Trainer() 
	{
		super();
	}
	public Trainer(int id, String name,String email, List<Course> course /*,List<Batch> batch */) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	//	this.batch = batch;
		this.course = course; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
/*		
	public List<Batch> getBatch() {
		return batch;
	}
	public void setBatch(List<Batch> batch) {
		this.batch = batch;
	}
*/	
	
	public List<TrainerSubTopicAssociation> getTrainerTopicAssociations() {
		return trainerTopicAssociations;
	}
	public void setTrainerTopicAssociations(List<TrainerSubTopicAssociation> trainerTopicAssociations) {
		this.trainerTopicAssociations = trainerTopicAssociations;
	}
	
	/*
	public void setBatches(List<String> batches) 
	{
	    this.batches = batches;
	}
	  
	public List<String> getBatches()
	{
	    return batches;
    }
*/    
				
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	
	@Override
	public String toString() {
		return "Trainer [id=" + id + ", name=" + name + ", email=" + email +  ", Course=" + course +", batch=" + batch
				+"]";
	}	

}
