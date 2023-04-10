package com.crud.tasks;

import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TasksApplicationTests {
	@Autowired
	DbService service;

	@Test
	void contextLoads() {
	}

//	@Test
//	void testGetTask() {
//		//When&Then
//		Assertions.assertEquals("test1", service.getTask(1).getContent());
//	}

}
