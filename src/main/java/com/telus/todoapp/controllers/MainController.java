package com.telus.todoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telus.todoapp.models.TodoTicket;
import com.telus.todoapp.services.TodoTicketService;

@Controller
public class MainController {

  @Autowired
  private TodoTicketService ticketService;
  
  @GetMapping("/todos")
  @ResponseBody
  public List<TodoTicket> getAll(){
    return ticketService.readAll();
  }

  @GetMapping("/todos/{ticketId}")
  @ResponseBody
  public TodoTicket getOne(@PathVariable int ticketId){
    return ticketService.readOne(ticketId);
  }

  @PostMapping("/todos")
  @ResponseBody
  public TodoTicket postOne(@RequestBody TodoTicket ticket){
    boolean canPost = 
      ticket.getId() == 0 &&
      ticket.getTitle() != null &&
      ticket.getTitle().length() < 255
    ;
    if (!canPost){
      return new TodoTicket();
    }


    if (ticket.getStatus() == null) {
      ticket.setStatus(TodoTicket.TodoTicketStatus.BACKLOG);
    }

    ticketService.create(ticket);

    return ticket;
  }
}
