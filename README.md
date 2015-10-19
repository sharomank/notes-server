# Notes Server
[![Build Status](https://travis-ci.org/sharomank/notes-server.svg)](https://travis-ci.org/sharomank/notes-server)
[![Coverage Status](https://coveralls.io/repos/sharomank/notes-server/badge.svg?branch=master&service=github)](https://coveralls.io/github/sharomank/notes-server?branch=master)
[![Coverity Status](https://scan.coverity.com/projects/5985/badge.svg)](https://scan.coverity.com/projects/5985)

Simple server-side app with REST API for notes.

## Prerequirements
* Java 8
* Docker 1.7

## Technologies
* Spring Boot with Web, Actuator & Data Mongo
* Gradle Wrapper

## Get started
* **Start Docker:** sudo service docker start
* **Run MongoDB:** docker run --rm --name mongodb -p 27017:27017 mongo
* **Run application:** ./gradlew bootRun
* **Open:** http://localhost:9001/health