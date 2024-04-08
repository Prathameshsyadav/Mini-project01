package com.mini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.entities.Counsellors;
import com.mini.repo.CounsellorsRepository;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
	CounsellorsRepository counRepo;

	@Override
	public Boolean saveCounsellor(Counsellors coun) {
		Counsellors findByEmail = counRepo.findByEmail(coun.getEmail());
		if(findByEmail != null) {
			return false;
		} else {
		Counsellors couns = counRepo.save(coun);
		return couns.getId() != null;
		}
	}

	@Override
	public Counsellors getCouncellor(String email, String pwd) {
		return counRepo.findByEmailAndPassword(email, pwd);
		
	}

}
