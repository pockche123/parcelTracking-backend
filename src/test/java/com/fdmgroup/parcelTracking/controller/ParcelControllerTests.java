package com.fdmgroup.parcelTracking.controller;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fdmgroup.parcelTracking.model.Parcel;
import com.fdmgroup.parcelTracking.service.ParcelService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ParcelControllerTests {

	@Mock
	private ParcelService parcelService;

	@InjectMocks
	private ParcelController parcelController;

	@Test
	@DisplayName("Get All Parcels")
	public void testGetAllParcels_returnParcelsExpected() {
		Parcel parcel1 = new Parcel("si33aGB", "Wiz Burk", "Dispatched", "54 Night Street, Galway, G44 5HH", 5);
		Parcel parcel2 = new Parcel("bigb0izGB", "Peter Burr", "Dispatched", "Hounslow", 6);

		List<Parcel> expected = Arrays.asList(parcel1, parcel2);

		when(parcelService.getAllParcels()).thenReturn(expected);

		List<Parcel> actual = parcelController.getParcels();

		assertEquals(expected, actual);
		verify(parcelService).getAllParcels();
	}

	@Test
	@DisplayName("Get Parcel")
	public void testGetParcel_ReturnNullOrParcel_whenNoParcelorParcelIsPresent() {
		Parcel parcel1 = new Parcel("si33aGB", "Wiz Burk", "Dispatched", "54 Night Street, Galway, G44 5HH", 5);
		parcel1.setId(1);

		when(parcelService.getParcel(1)).thenReturn(parcel1);

		ResponseEntity<Parcel> response1 = parcelController.getParcel(1);
		assertEquals(parcel1, response1.getBody());

		verify(parcelService).getParcel(1);

		when(parcelService.getParcel(2)).thenReturn(null);

		ResponseEntity<Parcel> response2 = parcelController.getParcel(2);

		assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
	}

	@Test
	@DisplayName("Create Parcel")
	public void testCreateParcel_returnCreatedStatusWhenCreated_ConflictStatusWhenNotFound() {
		Parcel parcel = new Parcel();
		when(parcelService.createParcel(parcel)).thenReturn(parcel);
		when(parcelService.createParcel(null)).thenReturn(null);

		ResponseEntity<Parcel> response1 = parcelController.createParcel(parcel);
		ResponseEntity<Parcel> response2 = parcelController.createParcel(null);

		assertEquals(HttpStatus.CREATED, response1.getStatusCode());
		assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());

		verify(parcelService).createParcel(parcel);
	}
git 
	@Test
	@DisplayName("Delete Parcel")
	public void testDeleteParcel_returnOkStatusWhenParcelPresent_returnNotFoundStatusWhenNotPresent() {
		Parcel parcel1 = new Parcel("si33aGB", "Wiz Burk", "Dispatched", "54 Night Street, Galway, G44 5HH", 5);
		parcel1.setId(1);

		when(parcelService.removeParcel(1)).thenReturn(true);
		when(parcelService.removeParcel(2)).thenReturn(false);

		ResponseEntity<Void> response1 = parcelController.deleteParcel(1);
		ResponseEntity<Void> response2 = parcelController.deleteParcel(2);

		assertEquals(HttpStatus.OK, response1.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

		verify(parcelService).removeParcel(1);

	}

	@Test
	@DisplayName("Update Parcel")
	public void testUpdateParcel_returnOkStatusWhenUpdated_nullWhenNullIsCalled() {
		Parcel parcel = new Parcel();
		when(parcelService.updateParcel(parcel, 1)).thenReturn(parcel);
		when(parcelService.updateParcel(null, 1)).thenReturn(null);

		ResponseEntity<Parcel> response1 = parcelController.updateParcel(parcel, 1);
		ResponseEntity<Parcel> response2 = parcelController.updateParcel(null, 1);

		assertEquals(HttpStatus.OK, response1.getStatusCode());
		assertEquals(parcel, response1.getBody());
		assertEquals(HttpStatus.OK, response2.getStatusCode());
		assertEquals(null, response2.getBody());

		verify(parcelService).updateParcel(parcel, 1);

	}

}