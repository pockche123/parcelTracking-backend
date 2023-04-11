package com.fdmgroup.parcelTracking.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.parcelTracking.model.Parcel;
import com.fdmgroup.parcelTracking.service.ParcelService;

@RestController
@RequestMapping("api/v1/parcel")
@CrossOrigin(origins="http://localhost:3000")
public class ParcelController{
	
	
	@Autowired
	private ParcelService parcelService;
	
	@GetMapping 
	public List<Parcel> getParcels(){
		return parcelService.getAllParcels();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Parcel> getParcel(@PathVariable int id){
		Parcel parcel = parcelService.getParcel(id);
		
		if(parcel != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(parcel);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParcel(@PathVariable int id){
		if(parcelService.removeParcel(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<Parcel> createParcel(@RequestBody Parcel parcel){
		Parcel createdParcel = parcelService.createParcel(parcel);
		
		if(createdParcel != null) {
			URI location = ServletUriComponentsBuilder
					            .fromCurrentRequest()
					            .path("/{parcelId}")
					            .buildAndExpand(createdParcel.getId())
					            .toUri();
			return ResponseEntity
					.created(location)
					.build();
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Parcel> updateParcel(@RequestBody Parcel parcel, @PathVariable int id){
		return ResponseEntity.ok(parcelService.updateParcel(parcel, id));
	}
	
	
	
	
}