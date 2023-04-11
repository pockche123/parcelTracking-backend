package com.fdmgroup.parcelTracking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.parcelTracking.exception.ResourceNotFoundException;
import com.fdmgroup.parcelTracking.model.Parcel;
import com.fdmgroup.parcelTracking.repository.ParcelDAO;

@Service
public class ParcelService{
	
	@Autowired
	private ParcelDAO parcelDao;
	
	public List<Parcel> getAllParcels() {
		return parcelDao.findAll();
	}
	
	public Parcel createParcel(Parcel parcel) {
		if(!parcelDao.existsById(parcel.getId())) {
			return parcelDao.save(parcel);
		}
		return null;
	}
	
	public Parcel getParcel(int parcelId) {
		Optional<Parcel> parcel = parcelDao.findById(parcelId);
		
		if(parcel.isPresent()) {
			return parcel.get();
		}
		return null;	
	}
	
	public Parcel updateParcel(Parcel parcel, int id) {
		Optional<Parcel> parcelOpt = parcelDao.findById(id);
		if(parcelOpt.isEmpty()) {
			throw new ResourceNotFoundException(getErrorMessage(id));
		}
		parcel.setId(id);
		return parcelDao.save(parcel);
	}

	private String getErrorMessage(int id) {
		return "Parcel with the id of " + id + " not found.";
	}
	
	public boolean removeParcel(int id) {
		if(parcelDao.existsById(id)) {
			parcelDao.deleteById(id);
			return true;
		}
		return false;
	}
	
	
	
	
}