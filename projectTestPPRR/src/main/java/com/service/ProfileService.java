package com.service;

import java.util.List;

import com.model.Profile;

public interface ProfileService 
{
	Profile addProfile(Profile profile);
	
	public Profile getOneProfile(int id);
	
	public Profile updateProfile(Profile p);

	public List<Profile> deleteProfile(int id);

	public List<Profile> getAllProfile();
	
	public List<Profile> find(String email);
	
//	public List<Profile> findByName(String name);
}
