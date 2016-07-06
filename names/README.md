Name Database Demo

This small application creates and maintains a database of names.  It is not a full featured or enterprise ready application.  It makes use of the following technologies:
* Spring Boot
* Spring Data
* Thymeleaf
* MongoDB
* Gender-api

Java 1.8 JDK and at least the Community Edition of MongoDB need to be installed.

There are two jar files that are associated with the solution:
* namesApp.jar – self-contained, executable jar file
* namesApp-sources.jar – source files for the solution

Running the application

* The application can be run via the command ‘java –jar namesApp.jar’.
* Make sure port 8080 is not already in use and in a browser enter ‘http://localhost:8080/home’
* A database named ‘people’ will be created on MongoDB with a collection called ‘names’.
* Initially, no names will be shown.  The names will appear as you add them.
* First and Last name are required
* To get the full list of names after a Search, just hit Search again making sure the search field is empty.
