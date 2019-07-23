package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.interfaces.AccountDao;
import com.epam.petersburg.ncr41.dao.interfaces.CardDao;
import com.epam.petersburg.ncr41.dao.interfaces.UserDao;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import com.epam.petersburg.ncr41.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class RegistrationServiceTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Registration service tests start");
    }

    @AfterAll
    static void done() {
        System.out.println("Registration service tests finished");
    }

    @Test
    void saveUser() {
        User user = new User(123, "TestName", "TestLastName", "test@email.com", "password", 2);
        RegistrationService registrationService = getRegistrationService(user);
        assertTrue(registrationService.saveUser());
    }

    @Test
    void saveUserFail() {
        User user = new User();
        RegistrationService registrationService = getRegistrationService(user);
        assertFalse(registrationService.saveUser());
    }

    private RegistrationService getRegistrationService(User user) {
        RegistrationService registrationService = new RegistrationService(user);
        registrationService.userDao = Mockito.mock(UserDao.class);
        registrationService.accountDao = Mockito.mock(AccountDao.class);
        registrationService.cardDao = Mockito.mock(CardDao.class);
        Optional<User> muser = Optional.of(user);
        when(registrationService.userDao.create(any(User.class))).thenReturn(muser);
        when(registrationService.accountDao.create(any(Account.class))).thenReturn(Optional.of(new Account("100", 123)));
        when(registrationService.cardDao.create(any(Card.class))).thenReturn(Optional.of(new Card()));
        List<Account> list = new ArrayList<>();
        list.add(new Account("100", 123));
        when(registrationService.accountDao.findAll()).thenReturn(list);
        return registrationService;
    }
}