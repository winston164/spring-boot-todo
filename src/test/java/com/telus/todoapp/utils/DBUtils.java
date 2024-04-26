package com.telus.todoapp.utils;

import org.springframework.jdbc.core.JdbcTemplate;

public class DBUtils {

  public static void wipeDB(JdbcTemplate jdbcTemplate){
    jdbcTemplate.execute("""
      DROP TABLE todo_tickets
    """);
    jdbcTemplate.execute("""
      CREATE TABLE IF NOT EXISTS todo_tickets (
        id INTEGER PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        status INT NOT NULL
      )
    """);
  }

}
