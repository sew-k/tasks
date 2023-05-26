package com.crud.tasks.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminConfigTestSuite {
    @Autowired
    AdminConfig adminConfig;

    @Test
    void shouldFetchRealAdminEmail() {
        //When
        String fetchedEmail = adminConfig.getAdminMail();

        //Then
        assertEquals("sew_k@wp.pl", fetchedEmail);
    }
}