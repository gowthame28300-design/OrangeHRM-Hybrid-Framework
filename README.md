# OrangeHRM Hybrid Automation Framework

## Project Overview

This project is a UI test automation framework for the OrangeHRM
application, developed using Java and Selenium WebDriver.

The framework is designed to organize test scripts, improve code
reusability, and support maintainable automated testing.

## Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Apache POI (Excel test data, if configured)
- Extent Reports (if configured)
- Git and GitHub

## Features

- Login and logout testing
- Employee creation
- Employee search
- Page Object Model for reusable page interactions
- TestNG-based test execution
- Maven dependency management
- Test data utilities
- Screenshot capture and test reporting, where configured
- Cross-browser execution, where configured

## Framework Structure

The project uses a Maven-based structure.    

```text
OrangeHRM-Hybrid-Framework/
├── src/
│   ├── main/
│   └── test/
├── TestData/
├── pom.xml
├── testng.xml
├── master.xml
├── .gitignore
└── README.md  
