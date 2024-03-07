package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.TrainerSubTopicAssociation;
import com.service.TsaService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/tsa")
public class TsaController 
{
	
	@Autowired
	TsaService tsaService;

	@PostMapping("/add")
	public ResponseEntity<TrainerSubTopicAssociation> saveSubTopics(@RequestBody TrainerSubTopicAssociation tsa)
	{
		TrainerSubTopicAssociation ob = tsaService.addTsa(tsa);
		return ResponseEntity.status(HttpStatus.CREATED).header("Add", "Tsa Created").body(ob);
		//return tsaService.addTsa(tsa);
	}
	
	@GetMapping("/getOne/{id}")
	public ResponseEntity<TrainerSubTopicAssociation>findOneTsa(@PathVariable("id") int id )
	{
		TrainerSubTopicAssociation ob = tsaService.getOneTsa(id);
		return ResponseEntity.ok(ob);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List <TrainerSubTopicAssociation> >findAllTsa()
	{
		List<TrainerSubTopicAssociation> sub = tsaService.getAllTsa();
		return ResponseEntity.ok(sub);
	}
	
	@PutMapping("/update")
	public ResponseEntity<TrainerSubTopicAssociation> updateTsa(@RequestBody TrainerSubTopicAssociation tsa)
	{
		TrainerSubTopicAssociation updatedTsa = tsaService.updateTsa(tsa);
		return ResponseEntity.ok(updatedTsa);
	}
}
