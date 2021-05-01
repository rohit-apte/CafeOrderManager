# CafeOrderManager

# Introduction 
 This project is a Java Spring boot based back end service and can be used to calculate the amount paid by a user or the amount a user owes.
 
# Getting Started
 Pre-requisites (Software to be installed on machine)
 1. Java 11 
 2. Maven 3.6.1 or higher
 3. Git
 
# Checkout source code
 1. Run command `git.exe clone --branch development -v "https://github.com/rohit-apte/CafeOrderManager.git"`
 
# Build and Test
 1.	Run command `cd CafeOrderManager`
 2.	Run command `mvn clean install` or `mvnw clean install` to build the maven project
 3.	Run command `mvn spring-boot:run` or `mvnw spring-boot:run` command to run spring boot project

# Calling the endpoints
Use the below given GET url to get the amount paid by a user. (Replace username by a user name)
- `http://localhost:8080/amount/paid/<username>`

Use the below GET url to get the amount a user owes
- `http://localhost:8080/amount/owes/<username>>`