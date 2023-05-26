package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTestSuite {
    @InjectMocks
    TrelloService trelloService;
    @Mock
    TrelloClient trelloClient;
    @Mock
    SimpleEmailService emailService;
    @Mock
    AdminConfig adminConfig;
    static final String SUBJECT = "subject";
    @Test
    void shouldCallTrelloClientMethod() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> resultList = trelloService.fetchTrelloBoards();

        //Then
        verify(trelloClient, atLeastOnce()).getTrelloBoards();
        assertEquals(trelloBoardDtoList, resultList);
    }
    @Test
    void shouldCreateTrelloCardDto() {
        //Given
        when(adminConfig.getAdminMail()).thenReturn("test@test.pl");
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        trelloCardDto.setName("test name");
        trelloCardDto.setDescription("test description");
        trelloCardDto.setPos("pos1");
        trelloCardDto.setListId("1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        createdTrelloCardDto.setId("1");
        createdTrelloCardDto.setName(trelloCardDto.getName());
        createdTrelloCardDto.setShortUrl("test");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto fetchedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), fetchedTrelloCardDto.getName());
        assertEquals(trelloCardDto.getListId(), fetchedTrelloCardDto.getId());
        assertEquals("test", fetchedTrelloCardDto.getShortUrl());
    }
    @Test
    void shouldSendEmailToAdmin() {
        //Given
        when(adminConfig.getAdminMail()).thenReturn("test@test.pl");
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "test name",
                "test description",
                "pos1",
                "1"
        );
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto(
                "1",
                trelloCardDto.getName(),
                "test"
        ));

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        verify(adminConfig, atMostOnce()).getAdminMail();
        verify(emailService, atLeastOnce()).send(any());
        assertEquals(trelloCardDto.getName(), createdTrelloCardDto.getName());
    }
}