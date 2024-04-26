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

  @Test
  void createOne() {
    // Prepare 
    assertThat(ticketService).isNotNull();
    TodoTicket ticket = new TodoTicket();
    ticket.setTitle("test");
    ticket.setDescription("desc");
    ticket.setStatus(TodoTicket.TodoTicketStatus.BACKLOG);

    // Test
    ticketService.create(ticket);

    // Assert
    assertThat(ticket.getId()).isEqualTo(1);
  }

  @Test
  void persistOne() {
    // Prepare 
    assertThat(ticketService).isNotNull();
    TodoTicket ticket = new TodoTicket();
    ticket.setTitle("test");
    ticket.setDescription("desc");
    ticket.setStatus(TodoTicket.TodoTicketStatus.BACKLOG);
    ticketService.create(ticket);

    // Test
    TodoTicket responseTicket = ticketService.readOne(ticket.getId());

    // Assert
    assertThat(responseTicket.getTitle()).isEqualTo(ticket.getTitle());
    assertThat(responseTicket.getId()).isEqualTo(ticket.getId());
    assertThat(responseTicket.getDescription()).isEqualTo(ticket.getDescription());
    assertThat(responseTicket.getStatus()).isEqualTo(ticket.getStatus());

  }
}
