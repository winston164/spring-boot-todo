package com.telus.todoapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.todoapp.daos.TodoTicketDAO;
import com.telus.todoapp.models.TodoTicket;

@Service
public class TodoTicketServiceImpl implements TodoTicketService {

  @Autowired
  private TodoTicketDAO ticketDAO;

  @Override
  public List<TodoTicket> readAll(){
    return ticketDAO.findAll();
  }

  @Override
  public TodoTicket readOne(int id){
    return ticketDAO.findOne(id);
  }

  @Override
  public boolean create(TodoTicket ticket){
    return ticketDAO.save(ticket);
  }

  @Override
  public boolean update(int id, TodoTicket ticket){

    TodoTicket oldTicket = ticketDAO.findOne(id);
    if (ticket.getTitle() == null){
      ticket.setTitle(oldTicket.getTitle());
    }
    if (ticket.getDescription() == null){
      ticket.setDescription(oldTicket.getDescription());
    }
    if (ticket.getStatus() == null){
      ticket.setStatus(oldTicket.getStatus());
    }

    return ticketDAO.updateOne(id, ticket);
  }
}
