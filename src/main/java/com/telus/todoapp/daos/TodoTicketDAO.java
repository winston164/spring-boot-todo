package com.telus.todoapp.daos;

import java.util.List;

import com.telus.todoapp.models.TodoTicket;

public interface TodoTicketDAO {

  public List<TodoTicket> findAll();

  public TodoTicket findOne(int id);

  public boolean save(TodoTicket ticket);

  public boolean updateOne(int id, TodoTicket ticket);

  public boolean deleteOne(int id);
}
