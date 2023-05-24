package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskMapperTestSuite {
    @Autowired
    TaskMapper taskMapper;
    @Test
    void testMapToTask() {
        //Given
        TaskDto testTaskDto = new TaskDto(1L, "test title 1", "test content 1");

        //When
        Task resultTask = taskMapper.mapToTask(testTaskDto);

        //Then
        Assertions.assertEquals(testTaskDto.getId(), resultTask.getId());
        Assertions.assertEquals(testTaskDto.getTitle(), resultTask.getTitle());
        Assertions.assertEquals(testTaskDto.getContent(), resultTask.getContent());
    }
    @Test
    void testMapToTaskDto() {
        //Given
        Task testTask = new Task(2L, "test title 2", "test content 2");

        //When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(testTask);

        //Then
        Assertions.assertEquals(testTask.getId(), resultTaskDto.getId());
        Assertions.assertEquals(testTask.getTitle(), resultTaskDto.getTitle());
        Assertions.assertEquals(testTask.getContent(), resultTaskDto.getContent());
    }
    @Test
    void testMapToTaskDtoList() {
        //Given
        Task testTask1 = new Task(3L, "test title 3", "test content 3");
        Task testTask2 = new Task(4L, "test title 4", "test content 4");
        List<Task> testTasksList = new ArrayList<>();
        testTasksList.add(testTask1);
        testTasksList.add(testTask2);

        //When
        List<TaskDto> resultTaskDtoList = taskMapper.mapToTaskDtoList(testTasksList);

        //Then
        Assertions.assertEquals(testTasksList.size(), resultTaskDtoList.size());
        Assertions.assertEquals(testTasksList.get(0).getTitle(), resultTaskDtoList.get(0).getTitle());
        Assertions.assertEquals(testTasksList.get(1).getTitle(), resultTaskDtoList.get(1).getTitle());
    }
}