package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper;

    private static List<TrelloBoard> testListOfBoards = new ArrayList<>();
    private static List<TrelloBoardDto> testListOfBoardsDto = new ArrayList<>();
    private static List<TrelloList> listOfTestLists1 = new ArrayList<>();
    private static List<TrelloList> listOfTestLists2 = new ArrayList<>();
    private static List<TrelloListDto> listOfTestLists1Dto = new ArrayList<>();
    private static List<TrelloListDto> listOfTestLists2Dto = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        //Given
        TrelloList testList1 = new TrelloList("1", "first test list", false);
        TrelloList testList2 = new TrelloList("2", "second test list", true);
        listOfTestLists1.add(testList1);
        listOfTestLists1.add(testList2);
        TrelloBoard testBoard1 = new TrelloBoard("1", "first test board", listOfTestLists1);

        TrelloList testList3 = new TrelloList("3", "third test list", false);
        TrelloList testList4 = new TrelloList("4", "fourth test list", true);
        listOfTestLists2.add(testList3);
        listOfTestLists2.add(testList4);
        TrelloBoard testBoard2 = new TrelloBoard("2", "second test board", listOfTestLists2);

        testListOfBoards.add(testBoard1);
        testListOfBoards.add(testBoard2);

        TrelloListDto testListDto1 = new TrelloListDto("1", "first test list", false);
        TrelloListDto testListDto2 = new TrelloListDto("2", "second test list", true);
        listOfTestLists1Dto.add(testListDto1);
        listOfTestLists1Dto.add(testListDto2);
        TrelloBoardDto testBoard1Dto = new TrelloBoardDto("1", "first test board", listOfTestLists1Dto);

        TrelloListDto testListDto3 = new TrelloListDto("3", "third test list", false);
        TrelloListDto testListDto4 = new TrelloListDto("4", "fourth test list", true);
        listOfTestLists2Dto.add(testListDto3);
        listOfTestLists2Dto.add(testListDto4);
        TrelloBoardDto testBoard2Dto = new TrelloBoardDto("2", "second test board", listOfTestLists2Dto);

        testListOfBoardsDto.add(testBoard1Dto);
        testListOfBoardsDto.add(testBoard2Dto);
    }

    @Test
    void testMapToBoards() {
        //When
        List<TrelloBoard> resultListOfBoards = trelloMapper.mapToBoards(testListOfBoardsDto);

        //Then
        Assertions.assertEquals(testListOfBoardsDto.size(), resultListOfBoards.size());
        Assertions.assertEquals(testListOfBoardsDto.get(0).getName(), resultListOfBoards.get(0).getName());
        Assertions.assertEquals(testListOfBoardsDto.get(0).getId(), resultListOfBoards.get(0).getId());
        Assertions.assertEquals(testListOfBoardsDto.get(1).getName(), resultListOfBoards.get(1).getName());
        Assertions.assertEquals(testListOfBoardsDto.get(1).getId(), resultListOfBoards.get(1).getId());
    }
    @Test
    void testMapToBoardsDto() {
        //When
        List<TrelloBoardDto> resultListOfBoardsDto = trelloMapper.mapToBoardsDto(testListOfBoards);

        //Then
        Assertions.assertEquals(testListOfBoards.size(), resultListOfBoardsDto.size());
        Assertions.assertEquals(testListOfBoards.get(0).getName(), resultListOfBoardsDto.get(0).getName());
        Assertions.assertEquals(testListOfBoards.get(0).getId(), resultListOfBoardsDto.get(0).getId());
        Assertions.assertEquals(testListOfBoards.get(1).getName(), resultListOfBoardsDto.get(1).getName());
        Assertions.assertEquals(testListOfBoards.get(1).getId(), resultListOfBoardsDto.get(1).getId());
    }
    @Test
    void testMapToList() {
        //When
        List<TrelloList> resultListOfLists = trelloMapper.mapToList(listOfTestLists1Dto);

        //Then
        Assertions.assertEquals(listOfTestLists1Dto.size(), resultListOfLists.size());
        Assertions.assertEquals(listOfTestLists1Dto.get(0).getName(), resultListOfLists.get(0).getName());
        Assertions.assertEquals(listOfTestLists1Dto.get(0).getId(), resultListOfLists.get(0).getId());
        Assertions.assertEquals(listOfTestLists1Dto.get(1).getName(), resultListOfLists.get(1).getName());
        Assertions.assertEquals(listOfTestLists1Dto.get(1).getId(), resultListOfLists.get(1).getId());
    }
    @Test
    void testMapToListDto() {
        //When
        List<TrelloListDto> resultListOfListsDto = trelloMapper.mapToListDto(listOfTestLists1);

        //Then
        Assertions.assertEquals(listOfTestLists1.size(), resultListOfListsDto.size());
        Assertions.assertEquals(listOfTestLists1.get(0).getName(), resultListOfListsDto.get(0).getName());
        Assertions.assertEquals(listOfTestLists1.get(0).getId(), resultListOfListsDto.get(0).getId());
        Assertions.assertEquals(listOfTestLists1.get(1).getName(), resultListOfListsDto.get(1).getName());
        Assertions.assertEquals(listOfTestLists1.get(1).getId(), resultListOfListsDto.get(1).getId());
    }
    @Test
    void testMapToCardDto() {
        //Given
        TrelloCard testCard = new TrelloCard(
                "first test card",
                "test card to perform tests: 1",
                "99",
                "999"
        );

        //When
        TrelloCardDto resultCardDto = trelloMapper.mapToCardDto(testCard);

        //Then
        Assertions.assertEquals(testCard.getName(), resultCardDto.getName());
        Assertions.assertEquals(testCard.getDescription(), resultCardDto.getDescription());
        Assertions.assertEquals(testCard.getPos(), resultCardDto.getPos());
        Assertions.assertEquals(testCard.getListId(), resultCardDto.getListId());
    }
    @Test
    void testMapToCard() {
        //Given
        TrelloCardDto testCardDto = new TrelloCardDto(
                "first test card DTO",
                "test card DTO to perform tests: 1",
                "88",
                "888"
        );

        //When
        TrelloCard resultCard = trelloMapper.mapToCard(testCardDto);

        //Then
        Assertions.assertEquals(testCardDto.getName(), resultCard.getName());
        Assertions.assertEquals(testCardDto.getDescription(), resultCard.getDescription());
        Assertions.assertEquals(testCardDto.getPos(), resultCard.getPos());
        Assertions.assertEquals(testCardDto.getListId(), resultCard.getListId());
    }
}