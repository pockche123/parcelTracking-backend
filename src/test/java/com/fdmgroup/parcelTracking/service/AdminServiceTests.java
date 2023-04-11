package com.fdmgroup.parcelTracking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.parcelTracking.model.Admin;
import com.fdmgroup.parcelTracking.repository.AdminDAO;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTests {

	@Mock
	private AdminDAO adminDao;

	@InjectMocks
	private AdminService adminService;

	private Admin admin1, admin2;

	@BeforeEach
	void setup() {
		admin1 = new Admin("admin1", "password1");
		admin2 = new Admin("admin2", "password2");
	}

	@Test
	@DisplayName("Get All Admins")
	void givenListOfAdmins_whenFindAll_ReturnAllAdmins() {
		Iterable<Admin> actual = adminService.getAllAdmins();

		assertThat(actual).isNotNull();
		verify(adminDao).findAll();

	}

	@Test
	@DisplayName("Get Admin")
	void givenAdminId_WhenGetAdmin_ReturnAdminOrNull() {

		Admin expected = admin1;
		when(adminDao.findById(expected.getId())).thenReturn(Optional.of(expected));

		Admin actual = adminService.getAdmin(expected.getId());

		Admin noAdmin = adminService.getAdmin(-5);

		assertThat(actual).isEqualTo(expected);
		assertThat(noAdmin).isNull();
		verify(adminDao).findById(expected.getId());
	}

	@Test
	@DisplayName("Update Admin")
	void givenAdminAndId_whenUpdateAdmin_returnUpdatedAdmin() {

		Admin admin3 = new Admin("admin3", "password3");

		when(adminDao.findById(admin1.getId())).thenReturn(Optional.of(admin1));
		when(adminDao.save(admin3)).thenReturn(admin3);

		Admin updated = adminService.updateAdmin(admin3, admin1.getId());

		assertThat(updated.getPassword()).isEqualTo("password3");
		verify(adminDao).findById(admin3.getId());

	}

	@Test
	@DisplayName("Remove Admin")
	void givenAdminId_whenRemoveAdmin_thenReturnTrueOrFalse() {

		assertFalse(adminService.removeAdmin(-1));
	}

	@AfterEach
	void tearDown() {
		admin1 = null;
		admin2 = null;
	}

}
