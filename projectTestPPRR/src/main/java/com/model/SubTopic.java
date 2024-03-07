package com.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class SubTopic
{
	@Id
	private int sid;

	private String sname;

	private int shours;
	
	//private int hours_taken; 
	
	@JsonBackReference
	@OneToMany(mappedBy = "subtopic",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TrainerSubTopicAssociation> trainerTopicAssociations;

	
	public SubTopic() {
		super();
	}


	public SubTopic(int sid, String sname, int shours) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.shours = shours;
		//this.hours_taken = hours_taken;
	}


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public int getShours() {
		return shours;
	}


	public void setShours(int shours) {
		this.shours = shours;
	}
	
	
	public List<TrainerSubTopicAssociation> getTrainerTopicAssociations() {
		return trainerTopicAssociations;
	}


	public void setTrainerTopicAssociations(List<TrainerSubTopicAssociation> trainerTopicAssociations) {
		this.trainerTopicAssociations = trainerTopicAssociations;
	}


	/*
	public Topic getTopic() {
		return topic;
	}


	public void setTopic(Topic topic) {
		this.topic = topic;
	}

*/
	@Override
	public String toString() {
		return "SubTopics [sid=" + sid + ", sname=" + sname + ", shours=" + shours +  "]";
	}
}
