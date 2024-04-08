package com.mini.services;

import com.mini.entities.Counsellors;

public interface CounsellorService {
	
	public Boolean saveCounsellor(Counsellors coun);
	
	public Counsellors getCouncellor(String email, String pwd);

}
