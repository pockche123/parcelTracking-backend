package com.fdmgroup.parcelTracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.parcelTracking.model.Admin;
import com.fdmgroup.parcelTracking.service.AdminService;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public List<Admin> getAllAdmins(){
	 
  
	return adminService.getAllAdmins();		
}
	
	
//	@GetMapping 
//	public String getMessage() {
//		String message = "hello world";
//		return message;
//	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdmin(@PathVariable int id){
	   Admin admin = adminService.getAdmin(id);
	   if(admin != null) {
		   return ResponseEntity.status(HttpStatus.OK).body(admin);
	   }
	   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable int id){
		if(adminService.removeAdmin(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable int id) {
	
      return ResponseEntity.ok(adminService.updateAdmin(admin, id));
	}

	
	
	
	
}