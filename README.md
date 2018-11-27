# Spring Boot Application

A Spring boot Web based Application that allows users to view the latest documents and text uploaded by the admin or manager. Previous uploads are 
stored in MySql Database.

### Design Pattern is Model - View - Controller


## Built With 
 - Spring Boot - Spring based production ready project initializer for Stand alone Applications
 - Spring MVC (Model View Controller) - HTTP oriented MVC framework
 - HTML CSS and JS for the basic front end
 - MySql for database

### Steps to Setup

**1. Clone the repository** 

```bash
git clone https://github.com/tundeOlagunju/springBoot.git
```

**2. Configure MySQL database**

Create a MySQL database named `mysql`, and change the username and password in `src/main/resources/application.properties` as per user's MySQL
installation -

```properties
spring.datasource.username= <YOUR MYSQL USERNAME>
spring.datasource.password= <YOUR MYSQL PASSWORD>
```

**3. Run the app using maven**

```bash
cd spring-boot-file-upload-download-rest-api-example
mvn spring-boot:run
```

The application can be accessed at `http://localhost:8080`.

 The application can also be packaged in the form of a jar and then run the jar file like so -

```bash
mvn clean package
java -jar target/file-demo-0.0.1-SNAPSHOT.jar
```
