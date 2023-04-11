package com.fdmgroup.parcelTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.parcelTracking.model.Admin;



public interface AdminDAO extends JpaRepository<Admin, Integer> {


}
