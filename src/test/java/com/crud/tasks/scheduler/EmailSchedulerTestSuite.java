package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.Scheduled;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTestSuite {
    @InjectMocks
    EmailScheduler emailScheduler;
    @Mock
    SimpleEmailService simpleEmailService;
    @Mock
    TaskRepository taskRepository;
    @Mock
    AdminConfig adminConfig;
    private static final String SUBJECT = "Tasks: Once a day email";

    @Test
    void shouldSendInformationEmail() {
//        //Given
//        Mockito.when(taskRepository.count()).thenReturn(2l);
//        Mockito.when(adminConfig.getAdminMail()).thenReturn("test@test.pl");
//
//        //When
//        emailScheduler.sendInformationEmail();
//
//        //Then
//        Mockito.verify(simpleEmailService, Mockito.atLeastOnce()).send(any());
    }
}