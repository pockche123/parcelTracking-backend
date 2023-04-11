package com.fdmgroup.parcelTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.parcelTracking.model.Parcel;

public interface ParcelDAO extends JpaRepository<Parcel, Integer>{
	
}