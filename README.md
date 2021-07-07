# Airport managing system REST API
RESTful API for managing stuff and airplanes, scheduling flights and inspections  

## Table of contents
* [General info](#general-info)
* [ER diagram](#er-diagram)
* [Technologies](#technologies)
* [Requirements](#requirements)
* [Setup & Running](#setup-running)

## General info
This project is a course work in the discipline "Organization of databases and knowledge". 
The main goal was to create a web-service that can act as an additional layer between database and the user, 
so that it can be used in conjunction with other applications (ex. frontend, mobile, web-service) without giving away details of database implementation.

Full course work description can be found [here](https://smallpdf.com/result#r=b9c41c9bc466d98fc6f66160686a5824&t=share-document)

## ER Diagram
![alt text](https://i.ibb.co/HYY8QY7/image-2021-07-07-12-02-42.png)

## Technologies
Project is created with:
* Java SE 8
* Spring Boot
* Spring Data JPA
* Spring Data REST
* MySQL 8.0.5

## Requirements
To run this project, you need to install the folowing packages on your machine:
* Apache Maven version 3.3.9 or higher
* Java SE 8

## Setup & Running
```
$ git clone git@github.com:skiliton/airport-rest-api.git
$ cd airport-rest-api
$ export DB_URL=your_db_url
$ export DB_USERNAME=your_db_username
$ export DB_PASSWORD=your_db_password
$ mvn package
$ java -jar /target/airport-v1.jar
```
