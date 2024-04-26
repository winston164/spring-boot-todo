package com.telus.todoapp.daos;

import java.util.List;

import com.telus.todoapp.models.TodoTicket;

public interface TodoTicketDAO {

  public List<TodoTicket> findAll();

}
