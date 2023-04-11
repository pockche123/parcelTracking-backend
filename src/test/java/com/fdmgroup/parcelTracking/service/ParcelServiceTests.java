package com.fdmgroup.parcelTracking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.parcelTracking.model.Parcel;
import com.fdmgroup.parcelTracking.repository.ParcelDAO;

@ExtendWith(MockitoExtension.class)
public class ParcelServiceTests{
	
	@Mock
	private ParcelDAO parcelDao;
	
	@InjectMocks
	private ParcelService parcelService;
	
	private Parcel parcel1, parcel2;
	
	@BeforeEach
	void setup() {
		parcel1 = new Parcel("si33aGB", "Wiz Burk", "Dispatched", "54 Night Street, Galway, G44 5HH", 5);
		parcel2 = new Parcel("bigb0izGB", "Peter Burr", "Dispatched", "Hounslow", 6);
	}
	
	@Test
	@DisplayName("Get All Parcels")
	void givenListOfParcels_whenFindAll_returnAllParcels() {
		Iterable<Parcel> actual = parcelService.getAllParcels();
		assertThat(actual).isNotNull();
		verify(parcelDao).findAll();
	}
	
	
	@Test 
	@DisplayName("Get Parcel")
	void givenParcelId_whenGetParcel_returnParcelOrNull() {
		Parcel expected = parcel1;
		
		when(parcelDao.findById(expected.getId())).thenReturn(Optional.of(expected));
		
		Parcel actual = parcelService.getParcel(expected.getId());
		
		Parcel noParcel = parcelService.getParcel(-1);
		
		assertThat(actual).isEqualTo(expected);
		verify(parcelDao).findById(expected.getId());
		assertThat(noParcel).isNull();
		
	}
	
	@Test
	@DisplayName("Create Parcel")
	void givenParcelObject_whenCreateParcel_thenReturnParcelFromDB() {
		when(parcelDao.save(parcel1)).thenReturn(parcel1);
		
		Parcel actual = parcelService.createParcel(parcel1);
		
		assertThat(actual.getEstimated_delivery_days()).isEqualTo(5);
		verify(parcelDao).save(parcel1);
	}
	
	
	@Test 
	@DisplayName("Update Parcel")
	void givenParcelId_whenUpdateParcel_returnUpdatedParcel() {
		Parcel parcel3 = new Parcel("ke33aGB", "Mani Kane", "In transit", "Hounslow", 3);
		
		when(parcelDao.findById(parcel1.getId())).thenReturn(Optional.of(parcel1));
		when(parcelDao.save(parcel3)).thenReturn(parcel3);
		
		Parcel updated = parcelService.updateParcel(parcel3, parcel1.getId());
		
		assertThat(updated.getName()).isEqualTo("Mani Kane");
		verify(parcelDao).findById(parcel3.getId());
	}
	
	
	@Test
	@DisplayName("Remove Parcel")
	void givenParcelId_whenRemoveParcel_thenReturnFalse() {
		assertFalse(parcelService.removeParcel(-1));
	}
	
	
	
	@AfterEach
	void tearDown() {
		parcel1 = null;
		parcel2 = null;
	}
	
	
}