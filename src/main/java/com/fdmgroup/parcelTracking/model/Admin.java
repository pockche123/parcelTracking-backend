package com.fdmgroup.parcelTracking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "admins")
public class Admin {



		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adminIdGenerator")
		@SequenceGenerator(allocationSize = 1, name = "adminIdGenerator", sequenceName = "admin_seq")
		@Column(name = "admin_id")
		private int id;
		private String username;
		private String password;

		@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
		@JsonIgnore
		private List<Parcel> parcels;

		public Admin(String username, String password) {
			this.username = username;
			this.password = password;
			this.parcels = new ArrayList<>();
		}

		public Admin() {
			super();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Parcel> getParcels() {
			return parcels;
		}

		public void addParcel(Parcel Parcel) {
			this.parcels.add(Parcel);
		}

		@Override
		public String toString() {
			return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
		}

	}


