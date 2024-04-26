package com.telus.todoapp.daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.telus.todoapp.models.TodoTicket;
import com.telus.todoapp.models.TodoTicket.TodoTicketStatus;

public class TodoTicketMapper implements RowMapper<TodoTicket> {

  public TodoTicket mapRow(ResultSet rs, int row) throws SQLException {
    TodoTicket ticket = new TodoTicket();
    ticket.setId(rs.getInt("id"));
    System.out.println(rs.getString("id"));
    ticket.setTitle(rs.getString("title"));
    ticket.setDescription(rs.getString("description"));
    int statusValue = rs.getInt("status");
    TodoTicketStatus status = TodoTicketStatus.values()[statusValue];
    ticket.setStatus(status);
    return ticket;
  }

}
