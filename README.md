## Introduction

Simple URL shortened project created using Spring and Hibernate Frameworks. There are several APIs that allow you to
store your actual URL and access it using a simple and shorter URL.

###### Some Features
* After a ShortenedUrl entity is created successfully, an email will be sent to the specified address on the request body
  consisting of all required information about your shortened URL.
* You can determine a deletion time for your key. It'll automatically be deleted by a scheduled job.
* You need to run maildev before running the server. This project uses maildev as a fake SMTP server. You can reach
  all sent mail on http://localhost:1080 after running "maildev" command on the command line.
* Unit and integration tests are still in progress. I'll complete them as soon as possible (Hopefully :D).
* I'm planning to use vaadin Hilla Framework for the frontend and create a full-stack application in the future.

### Using Tools & Technologies

#### Backend
* Java 11
* Spring Boot 2.6.4 (with Spring Web, Spring Mail and Spring Data JPA)
* Postgresql Database
* Maven 3.6.3
* Mapstruct 1.4.2.Final
* Springdoc 1.5.12
* Thymeleaf 3.0.15.Release
* AssertJ 3.22.0
* Greenmail 1.6.7
* Awaitility 4.2.0
* Maildev (as fake SMTP server)

These are APIs that we need to provide:

| Method   | Route                                    | Parameter Type | Parameter Info                                                                                                                           | Description                          |
|----------|------------------------------------------|----------------|------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------|
| `POST`   | {{baseUrl}}/url-shortener                | Body           | {"realUrl": "string","accessKey": "string","validUntil": "2022-04-21T20:23:15.443Z","mail": "string"}                                    | Creating Shortened Url               |
| `GET`    | {{baseUrl}}/url-shortener/{accessKey}    | PathVariable   | accessKey                                                                                                                                | Forwards To The Actual URL           |
| `GET`    | {{baseUrl}}/url-shortener/get-by-id/{id} | PathVariable   | id                                                                                                                                       | Get Shortened URL ENTITY DTO         |
| `DELETE` | {{baseUrl}}/url-shortener/{deletionKey}  | deletionKey    | deletionKey                                                                                                                              | Delete Shortened URL By Deletion Key |
___

## :package: Package With Maven

On project root file:

```console
$ mvn clean install
```

## :clipboard: Run With Java

```console
$ java -jar target/url-shortener-0.0.1-SNAPSHOT.jar
```

