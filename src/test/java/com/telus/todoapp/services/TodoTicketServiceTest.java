package com.telus.todoapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import com.telus.todoapp.models.TodoTicket;
import com.telus.todoapp.utils.DBUtils;

@SpringBootTest
class TodoTicketServiceTest {

  @Autowired
  TodoTicketService ticketService;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @BeforeEach
  void beforeAll(){
    DBUtils.wipeDB(jdbcTemplate);
  }

  @Test
  void getEmptyReadAll(){
    // Prepare
    assertThat(ticketService).isNotNull();

    // Test
    List<TodoTicket> tickets = ticketService.readAll();

    //Assert
    assertThat(tickets.size()).isEqualTo(0);
  }

}
