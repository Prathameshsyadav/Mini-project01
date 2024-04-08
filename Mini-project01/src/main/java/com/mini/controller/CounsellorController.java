package com.mini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mini.dto.Dashboard;
import com.mini.entities.Counsellors;
import com.mini.services.CounsellorService;
import com.mini.services.EnquiryServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;
	@Autowired
	private EnquiryServices enquiryServices;
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("counsellors", new Counsellors());
		return "registerView";
	}
	
	@PostMapping("/register")
	public String handleRegister(Model m, Counsellors c) {
		Boolean counsellor = counsellorService.saveCounsellor(c);
		if(counsellor) {
			m.addAttribute("smsg","CounsellorSaved");
		}else {
			m.addAttribute("emsg", "Failed to save");
		}
		return "registerView";
	}
	
	@GetMapping("/")
	public String login(Model model) {
		//form binding
		model.addAttribute("counsellors", new Counsellors());
		return "index";
	}
	
	@PostMapping("/login") 
	public String handleLogin(Counsellors c,HttpServletRequest request, Model m) {
		Counsellors counsellor = counsellorService.getCouncellor(c.getEmail(), c.getPassword());
		if(counsellor == null) {
			m.addAttribute("emsg", "invalid credentials");
			return "index";
		}else {
			//set counsellor id in session
			HttpSession session = request.getSession(true);
			session.setAttribute("cid", counsellor.getId());
			Dashboard dashboard = enquiryServices.getDashboard(counsellor.getId());
			m.addAttribute("dashboard", dashboard);
			return "dashboard";
		}
	}
	
	@GetMapping("/dashboard")
	public String buildDashBoard(HttpServletRequest req, Model m) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");
		Dashboard dashboard = enquiryServices.getDashboard(cid);
		m.addAttribute("dashboard", dashboard);
		return "dashboard";
		
	}

}
