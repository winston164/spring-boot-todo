package com.telus.todoapp.services;

import java.util.List;

import com.telus.todoapp.models.TodoTicket;

public interface TodoTicketService {

  public List<TodoTicket> readAll();

  public TodoTicket readOne(int id);
}
