# Notes
Simple server with rest api for notes.

## Prerequirements
* Java 8
* Docker 1.7

## Technologies
* Spring Boot with Web, Actuator & Data Mongo
* Gradle 2.4

## Get started
* **Start Docker:** sudo service docker start
* **Run MongoDB:** docker run -d --name mongodb -p 27017:27017 mongo
* **Run application:** ./gradlew bootRun
* **Open:** http://localhost:8080/notes