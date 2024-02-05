# Advanced Programming project

This repository contains the source code of our solution for EFREI Paris who wants to develop a solution that will help improve the follow-up of internships.
It includes the following:
1. An electronic document management system to archive all the reports about internships.
2. An internship follow-up system for internship tutors.

## Readme documents

| List of readme.md                                |
|--------------------------------------------------|
| [Report Service](./Back-end-Report/readme.md)    |
| [Presentation Service](/Back-end/readme.md)      |
| [Authentication Service](auth-service/readme.md) |

## Diagrams

Here is our architecture diagram using docker:

![architecture diagram.png](architecture%20diagram.png)

In the future we would like to integrate our services to a Kubernetes cluster. Here is a diagram to illustrate our architecture:

![Diagram with Kube.png](Diagram%20with%20Kube.png)

## Swagger Documentation

A Swagger documentation has been generated for each of the backend services.

To access it, build the service of your choice and access the following URLs:

- http://localhost:8080/swagger-ui/index.html for the Student Presentation Service, or
- http://localhost:8082/swagger-ui/index.html for the Student Report Service, or
- http://localhost:8083/swagger-ui/index.html for the Authentication Service.