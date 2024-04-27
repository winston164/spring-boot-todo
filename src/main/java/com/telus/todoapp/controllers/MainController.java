package com.telus.todoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.telus.todoapp.models.TodoTicket;
import com.telus.todoapp.services.TodoTicketService;

@Controller
public class MainController {

  @Autowired
  private TodoTicketService ticketService;

  @ExceptionHandler
  public ResponseEntity<String> handleResponseStatusException(
    ResponseStatusException exc
  ){
    return new ResponseEntity<>(exc.getReason(), exc.getStatusCode());
  }
  
  @GetMapping("/todos")
  @ResponseBody
  public List<TodoTicket> getAll(){
    return ticketService.readAll();
  }

  @GetMapping("/todos/{ticketId}")
  @ResponseBody
  public TodoTicket getOne(@PathVariable int ticketId){
    try{
      return ticketService.readOne(ticketId);
    } catch (EmptyResultDataAccessException emptyResult){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + ticketId + " couldn't be found.");
    }
  }

  @PostMapping("/todos")
  @ResponseBody
  public TodoTicket postOne(@RequestBody TodoTicket ticket){
    if (ticket.getId() != 0){
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "To create a Todo entity do not set the id field in the request payload."
      );
    }
    if (ticket.getTitle() == null){
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "A title is required for a Todo entity."
      );
    }
    if (ticket.getTitle().length() > 255){
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "A title should be less than 255 characters for a Todo entity."
      );
    }


    if (ticket.getStatus() == null) {
      ticket.setStatus(TodoTicket.TodoTicketStatus.BACKLOG);
    }

    ticketService.create(ticket);

    return ticket;
  }

  @PatchMapping("/todos/{ticketId}")
  @ResponseBody
  public TodoTicket patchOne(
    @PathVariable int ticketId,
    @RequestBody TodoTicket ticket
  ){

    try{
      ticketService.readOne(ticketId);
    } catch (EmptyResultDataAccessException emptyResult){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + ticketId + " couldn't be found.");
    }
    
    ticketService.update(ticketId, ticket);

    ticket.setId(ticketId);

    return ticket;
  }

  @DeleteMapping("/todos/{ticketId}")
  @ResponseBody
  public void deleteOne(@PathVariable int ticketId){
    try{
      ticketService.readOne(ticketId);
    } catch (EmptyResultDataAccessException emptyResult){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with id " + ticketId + " couldn't be found.");
    }
    ticketService.delete(ticketId);
  }
  
}
