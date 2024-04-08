package com.mini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mini.entities.Enquiry;
import com.mini.services.EnquiryServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	EnquiryServices enqSer;
	
	//add enquiry
	@GetMapping("/enquiry")
	public String addEnquiry(Model m) {
		m.addAttribute("enq", new Enquiry());
		return "addEnq";
	}
	
	//save enquiry
	@PostMapping("/enquiry")
	public String saveEnquiry(Enquiry enq, Model m, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");
		
		Boolean save = enqSer.addEnquiry(enq, cid);
		
		if(save) {
			m.addAttribute("smsg", "Enquiry Saved");
		}else {
			m.addAttribute("emsg", "Enquiry not saved");
		}
		m.addAttribute("enq", new Enquiry());
		return "addEnq";
	}
	
	//view enquries
	@GetMapping("/enquiries")
	public String getEnquiry(HttpServletRequest req, Model m) {
		
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");
		List<Enquiry> list = enqSer.getEnquiries(new Enquiry(), cid);
		
		m.addAttribute("enqs", list);
		m.addAttribute("enq", new Enquiry());
		return "viewEnquries";
		
	}
	
	//filter enquries
	@PostMapping("/filter-enqs")
	public String filterEnqs(Model m, Enquiry enq, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");
		
		List<Enquiry> list = enqSer.getEnquiries(enq, cid);
		m.addAttribute("enqs", list);
		
		return "viewEnquries";
		
	}
	
	@GetMapping("/edit")
	public String editEnquiry(@RequestParam("enqId") Integer enqId, Model m) {
		Enquiry enquiry = enqSer.getEnquiry(enqId);
		m.addAttribute("enq", enquiry);
		return "addEnq";
	}
	
	
	
	

}
