package com.mini.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mini.dto.Dashboard;
import com.mini.entities.Counsellors;
import com.mini.entities.Enquiry;
import com.mini.repo.CounsellorsRepository;
import com.mini.repo.EnquiresRepository;

@Service
public class EnquiryServiceImpl implements EnquiryServices {
	
	@Autowired
	private EnquiresRepository enqRepo;
	@Autowired
	private CounsellorsRepository counRepo;

	@Override
	public Dashboard getDashboard(Integer councellorId) {
		// TODO Auto-generated method stub
		Long totalenq = enqRepo.getEnquiries(councellorId);
		Long openenq = enqRepo.getEnquiries(councellorId, "New");//"openEnq");
		Long lostenq = enqRepo.getEnquiries(councellorId, "Lost");//"lostEnq");
		Long enrolledEnq = enqRepo.getEnquiries(councellorId,"Enrolled"); //enrolledEnq);
		
		Dashboard d = new Dashboard();
		d.setTotalEnq(totalenq);
		d.setOpenEnq(openenq);
		d.setLostEnq(lostenq);
		d.setEnrolledEnq(enrolledEnq);
		
		return d;
		
		
	}

	@Override
	public Boolean addEnquiry(Enquiry enquiry, Integer councellorId) {
	 Counsellors counsellors = counRepo.findById(councellorId).orElseThrow();
	 enquiry.setCounsellors(counsellors);
	 Enquiry save = enqRepo.save(enquiry);
	 return save.getId() != null;
		
	}

	@Override
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer councellorId) {
		// TODO Auto-generated method stub
		Counsellors counsellors = counRepo.findById(councellorId).orElseThrow();
		 enquiry.setCounsellors(counsellors);
		 //dynamic query creation
		 
		 Enquiry searchCriteria = new Enquiry();
		 if(null != enquiry.getCourse() && !"".equals(enquiry.getCourse())) {
			 searchCriteria.setCourse(enquiry.getCourse());
		 }
		 if(null != enquiry.getClassMode() && !"".equals(enquiry.getClassMode())) {
			 searchCriteria.setClassMode(enquiry.getClassMode());
		 }
		 if(null != enquiry.getStatus() && !"".equals(enquiry.getStatus())) {
			 searchCriteria.setStatus(enquiry.getStatus());
		 }
		 
		 
		Example<Enquiry> of = Example.of(searchCriteria);
		return enqRepo.findAll(of);
	}

	@Override
	public Enquiry getEnquiry(Integer enquiryId) {
		// TODO Auto-generated method stub
		Enquiry enq = enqRepo.findById(enquiryId).orElseThrow();
		return enq;
	}

}
