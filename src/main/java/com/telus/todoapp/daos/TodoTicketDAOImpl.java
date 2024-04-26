package com.telus.todoapp.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.telus.todoapp.models.TodoTicket;

import jakarta.annotation.PostConstruct;

@Repository
public class TodoTicketDAOImpl implements TodoTicketDAO {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  @PostConstruct
  public void postConstruct(){
    jdbcTemplate.execute("""
      CREATE TABLE IF NOT EXISTS todo_tickets (
        id INT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        status INT NOT NULL
      )
    """);
  }

  @Override
  public List<TodoTicket> findAll(){
    return jdbcTemplate.query("SELECT * FROM todo_tickets", new TodoTicketMapper());
  }

  @Override
  public TodoTicket findOne(int id){
    return jdbcTemplate.queryForObject("SELECT * FROM todo_tickets WHERE id = ?", new TodoTicketMapper(), id);
  }
}
