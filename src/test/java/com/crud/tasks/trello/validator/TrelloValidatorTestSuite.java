package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TrelloValidatorTestSuite {

    @Autowired
    TrelloValidator validator;

    @Test
    void shouldFilterTestTrelloBoards() {
        //Given
        TrelloBoard board1 = new TrelloBoard("1", "first board", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("2", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(board1);
        trelloBoardList.add(board2);

        //When
        List<TrelloBoard> validatedTrelloBoardList = validator.validateTrelloBoards(trelloBoardList);

        //Then
        Assertions.assertEquals(1, validatedTrelloBoardList.size());
        Assertions.assertFalse(validatedTrelloBoardList.contains(board2));
    }

}