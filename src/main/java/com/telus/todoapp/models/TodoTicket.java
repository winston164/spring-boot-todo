package com.telus.todoapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="todo_tickets")
public class TodoTicket{

  public enum TodoTicketStatus {
    BACKLOG, IN_PROGRESS, COMPLETED, DISMISSED;
  };

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name="id")
  private int id;

  @Column(name="title")
  private String title;

  @Column(name="description")
  private String description;

  @Column(name="status")
  @Enumerated(EnumType.ORDINAL)
  private TodoTicketStatus status;

  public TodoTicket() {

  }

  public TodoTicket(
    String title,
    String description,
    TodoTicketStatus status
  ){
    this.title = title;
    this.description = description;
    this.status = status;
  }

  @Override
  public String toString(){
    return "Student{"+
      "id=" + id + ", " +
      "title=" + title + ", " +
      "description=" + description + ", " +
      "status=" + status +
    "}";
  }

  public int getId(){
    return id;
  }
  public void setId(int id){
    this.id = id;
  }

  public String getTitle(){
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription(){
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public TodoTicketStatus getStatus(){
    return status;
  }
  public void setStatus(TodoTicketStatus status) {
    this.status = status;
  }
}

