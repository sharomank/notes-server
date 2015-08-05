# Notes Server
[![Build Status](https://travis-ci.org/sharomank/notes-server.svg)](https://travis-ci.org/sharomank/notes-server)
<a href="https://scan.coverity.com/projects/5985">
  <img alt="Coverity Scan Build Status"
       src="https://scan.coverity.com/projects/5985/badge.svg"/>
</a>

Simple server app with REST API for notes.

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
