package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.impl.RoleDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.UserDaoImpl;
import com.epam.petersburg.ncr41.model.Role;
import com.epam.petersburg.ncr41.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    private LoginService service = new LoginService();

    @BeforeEach
    void setUp() {
        System.out.println("Start testing Login Service.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Testing Login Service is finished.");
    }

    @Test
    void verification() {
        service.setUserDao(Mockito.mock(UserDaoImpl.class));
        User user = new User(3, "Maksim", "Golota", "Maksim_Golota@epam.com", "2411a7c6dc91c19a466277426111cd16:6efc9aaee374510d65a2f588e145eefd", 1);
        when(service.getUserDao().findByEmail("Maksim_Golota@epam.com")).thenReturn(user);
        assertEquals(user, service.verification("Maksim_Golota@epam.com", "password"));
        assertNotEquals(user, service.verification("maksim_golota@epam.com", "password"));
        assertNotEquals(user, service.verification("Maksim_Golota@epam.com", "Password"));
        assertNotEquals(user, service.verification("maksim_golota@hotmail.com", "password"));
        assertNull(service.verification("test", "test"));
        assertNull(service.verification(null, null));
        assertNull(service.verification("", ""));

    }

    @Test
    void checkRole() {
        service.setRoleDao(Mockito.mock(RoleDaoImpl.class));
        Role role1 = new Role(1, "admin");
        Role role2 = new Role(2, "user");
        when(service.getRoleDao().findOne(1)).thenReturn(role1);
        when(service.getRoleDao().findOne(2)).thenReturn(role2);
        assertEquals(role1, service.checkRole(1));
        assertNotEquals(role1, service.checkRole(2));
        assertNull(service.checkRole(3));
        assertNull(service.checkRole(0));
    }
}