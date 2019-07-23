package com.epam.petersburg.ncr41;

import com.epam.petersburg.ncr41.executors.PasswordHashing;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashingTest {

    @BeforeAll
    static void setUp() {
        System.out.println("PasswordHashing tests start");
    }

    @AfterAll
    static void done() {
        System.out.println("PasswordHashing tests finished");
    }

    @Test
    void validatePass() {
        assertTrue(PasswordHashing.validatePass("password".toCharArray(), PasswordHashing.hashPass("password".toCharArray())));
        assertFalse(PasswordHashing.validatePass("Password".toCharArray(), PasswordHashing.hashPass("password".toCharArray())));
        assertFalse(PasswordHashing.validatePass("password".toCharArray(), PasswordHashing.hashPass("Password".toCharArray())));
        assertNotEquals("password",PasswordHashing.hashPass("password".toCharArray()));
    }
}
