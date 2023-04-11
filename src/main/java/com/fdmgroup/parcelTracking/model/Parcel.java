package com.fdmgroup.parcelTracking.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fdmgroup.parcelTracking.model.Admin;

@Entity(name = "parcels")
public class Parcel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcelIdGenerator")
	@SequenceGenerator(allocationSize = 1, name = "parcelIdGenerator", sequenceName = "parcel_seq")
	@Column(name="parcel_id")
	private int id;
	private String tracking;
	private String status; 
	private String name;
	private String address;
	private int estimated_delivery_days;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public Parcel(String tracking, String name, String status, String address, int estimated_delivery_days) {
		this.tracking = tracking;
		this.name = name;
		this.status = status;
		this.address = address;
		this.estimated_delivery_days = estimated_delivery_days;
     	
	}
	
	public Parcel() {
		super();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getEstimated_delivery_days() {
		return estimated_delivery_days;
	}

	public void setEstimated_delivery_days(int estimated_delivery_days) {
		this.estimated_delivery_days = estimated_delivery_days;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}
