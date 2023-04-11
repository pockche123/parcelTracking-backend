package com.fdmgroup.parcelTracking.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parcelTracking.exception.ResourceNotFoundException;
import com.fdmgroup.parcelTracking.model.Admin;
import com.fdmgroup.parcelTracking.repository.AdminDAO;


@Service
public class AdminService {
	
	
	@Autowired
	private AdminDAO adminDao;
	
	public List<Admin> getAllAdmins(){
		return adminDao.findAll();
	}
	
	
	public Admin getAdmin(int adminId) {
		Optional<Admin> admin = adminDao.findById(adminId);
		if(admin.isPresent()) {
			return admin.get();
		}
		return null;
	}
	
	public Admin updateAdmin(Admin admin, int id) {
		Optional<Admin> adminOpt = adminDao.findById(id);
		if(adminOpt.isEmpty()) {
			throw new ResourceNotFoundException(getErrorMessage(id));
		}
		admin.setId(id);
		return adminDao.save(admin);
	}
	
	private String getErrorMessage(int id) {
		return "Admin with the id of " + id + " not found";
	}


	public boolean removeAdmin(int adminId) {
		if(adminDao.existsById(adminId)) {
			adminDao.deleteById(adminId);
			return true;
		}
		return false;
	}
	
	
}