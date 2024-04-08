package com.mini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.entities.Counsellors;

public interface CounsellorsRepository extends JpaRepository<Counsellors, Integer> {
	
	public Counsellors findByEmail(String email);
	
	public Counsellors findByEmailAndPassword(String email, String password);
	
}
