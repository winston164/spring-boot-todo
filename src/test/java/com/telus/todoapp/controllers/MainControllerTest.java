package com.telus.todoapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import com.telus.todoapp.models.TodoTicket;
import com.telus.todoapp.utils.DBUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MainControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  JdbcTemplate jdbcTemplate;

  @BeforeEach
  void beforeAll(){
    DBUtils.wipeDB(jdbcTemplate);
  }

  private String url(){
    return "http://localhost:" + port;
  }

  @Test
  void getManyWithoutData(){
    // Prepare 
    assertThat(restTemplate).isNotNull();

    // Test
    TodoTicket[] tickets = restTemplate.getForObject(url() + "/todos", TodoTicket[].class);

    // Assert
    assertThat(tickets.length).isZero();

  }

  @Test
  void getWithoutData(){
    // Prepare 
    assertThat(restTemplate).isNotNull();
    String path = "/todos/1";

    // Test
    ResponseEntity<String> ticketResponse = restTemplate.getForEntity(url() + path, String.class);

    // Assert
    assertThat(ticketResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(ticketResponse.getBody()).isEqualTo("Todo with id 1 couldn't be found.");
  }

  @Test
  void postPayloadWithId(){
    // Prepare 
    assertThat(restTemplate).isNotNull();
    String path = "/todos";

    TodoTicket testTicket = new TodoTicket();
    testTicket.setId(1);

    // Test
    ResponseEntity<String> ticketResponse = restTemplate.postForEntity(url() + path, testTicket, String.class);

    // Assert
    assertThat(ticketResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(ticketResponse.getBody()).isEqualTo("To create a Todo entity do not set the id field in the request payload.");
  }

  @Test
  void postPayloadWithoutTitle(){
    // Prepare 
    assertThat(restTemplate).isNotNull();
    String path = "/todos";

    TodoTicket testTicket = new TodoTicket();

    // Test
    ResponseEntity<String> ticketResponse = restTemplate.postForEntity(url() + path, testTicket, String.class);

    // Assert
    assertThat(ticketResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(ticketResponse.getBody()).isEqualTo("A title is required for a Todo entity.");
  }


  @Test
  void postPayloadWithLongTitle(){
    // Prepare 
    assertThat(restTemplate).isNotNull();
    String path = "/todos";

    TodoTicket testTicket = new TodoTicket();
    testTicket.setTitle("c".repeat(256));

    // Test
    ResponseEntity<String> ticketResponse = restTemplate.postForEntity(url() + path, testTicket, String.class);

    // Assert
    assertThat(ticketResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(ticketResponse.getBody()).isEqualTo("A title should be less than 255 characters for a Todo entity.");
  }
}
