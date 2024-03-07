package com.model;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Batch 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bid;
	
	@Column(name = "bname",unique=true)
	private String bname;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date creation_date;

	
	@ManyToMany(fetch=FetchType.EAGER)
	//@JsonBackReference
	@JoinTable(name = "batch_trainer",
    joinColumns = @JoinColumn(name = "batch_id"),
    inverseJoinColumns = @JoinColumn(name = "trainer_id"))
	private List<Trainer> trainers;
	
	public Batch() {
		super();
	}

	public Batch(int bid, String bname, List<Trainer> trainers, Date creation_date) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.trainers = trainers;
		this.creation_date = creation_date;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}
	
	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public void setTrainers(List<Trainer> trainers) {
		this.trainers = trainers;
	}

	public List<Trainer> getTrainers() {
		return trainers;
	}

	@Override
	public String toString() {
		return "Batch [bid=" + bid + ", bname=" + bname + ", trainers=" + trainers + "]";
	}

}
