package com.mini.services;

import java.util.List;

import com.mini.dto.Dashboard;
import com.mini.entities.Enquiry;

public interface EnquiryServices {
	
	public Dashboard getDashboard(Integer councellorId);
	
	public Boolean addEnquiry(Enquiry enquiry, Integer councellorId);
	
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer councellorId);
	
	public Enquiry getEnquiry(Integer enquiryId);

}
