package com.mini.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini.entities.Enquiry;

public interface EnquiresRepository extends JpaRepository<Enquiry, Integer> {
	
	@Query(value = "select count(*) from enquiry where id=:councellorId",
			nativeQuery=true
			)
	public Long getEnquiries(int councellorId);
	
	@Query(value = "select count(*) from enquiry where id=:councellorId and status=:status",
			nativeQuery = true
			)
	public Long getEnquiries(int councellorId, String status);

}
