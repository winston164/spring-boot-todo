package com.telus.todoapp.daos;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        id INTEGER PRIMARY KEY,
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

  @Override
  public boolean save(TodoTicket ticket){
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(
      conn -> {
        PreparedStatement ps = conn.prepareStatement(
          "INSERT INTO todo_tickets(title, description, status) VALUES (?,?,?)", 
          new String[] {"id"}
        );
        ps.setString(1, ticket.getTitle());
        ps.setString(2, ticket.getDescription());
        ps.setInt(3, ticket.getStatus().ordinal());
        return ps;
      },
      keyHolder
    );

    int id = keyHolder.getKey().intValue();

    ticket.setId(id);

    return id > 0;
  }
}
