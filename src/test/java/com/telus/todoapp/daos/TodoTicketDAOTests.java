package com.telus.todoapp.daos;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import com.telus.todoapp.models.TodoTicket;
import com.telus.todoapp.models.TodoTicket.TodoTicketStatus;
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

  @Test
  void saveOne(){
    // Prepare 
    assertThat(ticketDAO).isNotNull();
    TodoTicket todoTicket = new TodoTicket();
    todoTicket.setStatus(TodoTicketStatus.BACKLOG);
    todoTicket.setTitle("title");

    // Test
    boolean saved = ticketDAO.save(todoTicket);

    // Assert
    assertThat(saved).isTrue();
    assertThat(todoTicket.getId()).isEqualTo(1);
  }

  @Test
  void persistOne(){
    // Prepare 
    assertThat(ticketDAO).isNotNull();
    TodoTicket ticket = new TodoTicket();
    ticket.setStatus(TodoTicketStatus.BACKLOG);
    ticket.setTitle("title");
    assertThat(ticketDAO.save(ticket)).isTrue();

    // Test
    TodoTicket responseTicket = ticketDAO.findOne(ticket.getId());

    // Assert
    assertThat(responseTicket.getTitle()).isEqualTo(ticket.getTitle());
    assertThat(responseTicket.getId()).isEqualTo(ticket.getId());
    assertThat(responseTicket.getDescription()).isEqualTo(ticket.getDescription());
    assertThat(responseTicket.getStatus()).isEqualTo(ticket.getStatus());
  }

}
