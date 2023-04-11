package com.fdmgroup.parcelTracking.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fdmgroup.parcelTracking.model.Admin;
import com.fdmgroup.parcelTracking.service.AdminService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AdminControllerTests {
	
	@Mock
	private AdminService adminService;
	
	@InjectMocks
	private AdminController adminController;
	
	@Test
	@DisplayName("Get All Admins")
	public void testGetAdmins_returnAdminsExpected() {
		Admin admin1 = new Admin("admin1", "password1");
		Admin admin2 = new Admin("admin2", "password2");
		
		List<Admin> expected = Arrays.asList(admin1, admin2);
		
		when(adminService.getAllAdmins()).thenReturn(expected);
		
		List<Admin> actual = adminController.getAllAdmins();
		
		assertEquals(expected, actual);
		verify(adminService).getAllAdmins();
	}
	
	@Test
	@DisplayName("Get Admin")
	public void testGetAdmin_ReturnNullOrItem_WhenNoItemOrItemIsPresent() {
		Admin admin1 = new Admin("admin1", "password1");
		
		when(adminService.getAdmin(admin1.getId())).thenReturn(admin1);
		
		ResponseEntity<Admin> response1 = adminController.getAdmin(admin1.getId());
		
		assertEquals(admin1, response1.getBody());
		
		verify(adminService).getAdmin(admin1.getId());
		
		//for null
		when(adminService.getAdmin(2)).thenReturn(null);
		
		ResponseEntity<Admin> response2 = adminController.getAdmin(2);
		
		assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

	}
	
	
	@Test
	@DisplayName("Delete Admin")
	public void testDeleteAdmin_returnOkStatusWhenAdminPresent_returnNotFoundStatusWhenNotPresent() {
		Admin admin1 = new Admin("admin1", "password1");
		admin1.setId(1);
		
		when(adminService.removeAdmin(1)).thenReturn(true);
		when(adminService.removeAdmin(2)).thenReturn(false);
		
		ResponseEntity<Void> response1 = adminController.deleteAdmin(1);
		ResponseEntity<Void> response2 = adminController.deleteAdmin(2);
		
		assertEquals(HttpStatus.OK, response1.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
		
		verify(adminService).removeAdmin(1);
		
	}
	
	@Test
	@DisplayName("Update Admin")
	public void testUpdateAdmin_returnOkStatusWhenUpdated_nullwhenNullisCalled() {
		Admin admin = new Admin();
		when(adminService.updateAdmin(admin, 1)).thenReturn(admin);
		when(adminService.updateAdmin(null, 1)).thenReturn(null);
		
		ResponseEntity<Admin> response1 = adminController.updateAdmin(admin, 1);
		ResponseEntity<Admin> response2 = adminController.updateAdmin(null, 1);
		
		assertEquals(HttpStatus.OK, response1.getStatusCode());
		assertEquals(admin, response1.getBody());
		assertEquals(HttpStatus.OK, response2.getStatusCode());
		assertEquals(null, response2.getBody());
		
		verify(adminService).updateAdmin(admin, 1);
		
	}
}