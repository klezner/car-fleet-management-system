# Car Fleet Management System - Full App

System for managing a fleet of cars in the company.

## Table of Contents

* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)

<!-- * [License](#license) -->

## General Information

- App as a backend and frontend.
- App can help to manage a fleet of cars in the company.
- App also allows managing information about company, departments and employees, trips with refuelings and repairs,
  fleet (refueling) cards and car workshops.
- Data are received via forms.
- Date are presented in different ways on websites.
- App supports users security by logging.

## Technologies Used

- Java 8
- Maven
- Git
- Spring Boot 2.5.6
- Spring Data JPA
- MySQL Database
- Spring Validation
- Spring Security
- Thymeleaf
- Thymeleaf Extras for Spring Security
- Bootstrap
- Font Awesome

## Features

List the ready features here:

- Create, read, update, delete data about:
  - Companies
  - Departments
  - Employees
  - Cars
  - Trips
  - Refuelings
  - Fleet (refueling) cards
  - Repairs
  - Car workshops
- Car trips data reporting
- Car repair data reporting
- Car refuelings data reporting

## Screenshots

![Example screenshot](./ccfms.png)

## Setup

To try out the app run the following in command line:
> mvn compile

To build the JAR file run the following command:
> mvn package

To execute the JAR file run:
> java -jar target/carfleetmanagementsystem-0.0.1-SNAPSHOT.jar`

## Usage

All app paths are available on local server running on base url:
`http://localhost:8080/`

Available paths:

`/car`, `/fleet-card`, `/company`, `/department`, `/employee`, `/trip`, `/refueling`, `/repair`, `/workshop`
, `/login`, `/user/profile`

[comment]: <> (with basic auth credentials:)

[comment]: <> (`U: admin/ P: admin1`)

## Project Status

> Project is: _in progress_

## Room for Improvement

-

## Contact

Created by [@klezner](https://github.com/klezner) - feel free to contact me!


<!-- Optional -->
<!-- ## License -->
<!-- This project is open source and available under the [... License](). -->

<!-- You don't have to include all sections - just the one's relevant to your project -->
