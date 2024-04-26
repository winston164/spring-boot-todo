# Spring Boot Todos

This project is a solution to the to-do API challenge.
This challenge consists in making a TODO list REST API that persists the tasks in a SQLite database.

## Installation

This application requires at leaast the Java JDK 17.

Once Java is installed, get this repository:
```
git clone git@github.com:winston164/
```

Then go ahead and run the app in dev mode:
```
cd spring-todo
./mvnw spring-boot:run
```

Or if you want to compile to a jar file you may:
```
cd spring-todo
./mvnw package
java -jar target/*.jar
```

After installation the server will run in port `8000`.
