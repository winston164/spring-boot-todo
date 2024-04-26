package com.telus.todoapp.daos;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import com.telus.todoapp.models.TodoTicket;
import com.telus.todoapp.utils.DBUtils;

@SpringBootTest
class TodoTicketDAOTests {

  @Autowired
  TodoTicketDAO ticketDAO;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @BeforeEach
  void beforeAll(){
    DBUtils.wipeDB(jdbcTemplate);
  }

  @Test
  void getEmptyTicketList(){
    // Prepare 
    assertThat(ticketDAO).isNotNull();

    // Test
    List<TodoTicket> tickets = ticketDAO.findAll();

    // Assert
    assertThat(tickets.size()).isZero();
  }

}
