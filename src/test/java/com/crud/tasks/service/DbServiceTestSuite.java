package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DbServiceTestSuite {

    @InjectMocks
    DbService service;

    @Mock
    TaskRepository repository;

    private static List<Task> taskList = new ArrayList<>();
    private static Task task1;
    private static Task task2;

    @BeforeAll
    static void setUp() {
        task1 = new Task(1L, "first task", "content of first task");
        task2 = new Task(2L, "second task", "content of second task");
        taskList.add(task1);
        taskList.add(task2);
    }

    @Test
    void shouldFetchAllTasks() {
        //Given
        Mockito.when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> fetchedTaskList = service.getAllTasks();

        //Then
        Assertions.assertNotNull(fetchedTaskList);
        Assertions.assertEquals(2, fetchedTaskList.size());
        Assertions.assertEquals(task1.getContent(), fetchedTaskList.get(0).getContent());
        Assertions.assertEquals(task2.getContent(), fetchedTaskList.get(1).getContent());
    }
    @Test
    void shouldFetchTaskById() {
        //When & Then
        try {
            Task fetchedTask = service.getTask(1L);
            Assertions.assertNotNull(fetchedTask);
            Assertions.assertEquals(task1.getContent(), fetchedTask.getContent());
        } catch (Exception e) {

        }
    }
    @Test
    void shouldThrowExceptionBecauseOfIncorrectTaskId() {
        //Given
        //Mockito.when(repository.findAll()).thenReturn(taskList);

        //When & Then
        Assertions.assertThrows(TaskNotFoundException.class, () -> {
            Task fetchedTask = service.getTask(999L);
        });
    }
    @Test
    void shouldSaveNewTask() {
        //Given
        Task newTask = new Task(3L, "new task", "new task content");
        Mockito.when(repository.save(newTask)).thenReturn(newTask);

        //When
        service.saveTask(newTask);

        //Then
        Mockito.verify(repository, Mockito.atLeastOnce()).save(newTask);
    }
    @Test
    void shouldDeleteTaskById() {
        //Given

        //When
        service.deleteById(3L);

        //Then
        Mockito.verify(repository, Mockito.atLeastOnce()).deleteById(3L);
    }
}