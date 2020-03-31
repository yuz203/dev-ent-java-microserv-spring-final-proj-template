# dev-ent-java-microserv-spring-final-proj-template

This is a sample Spring Framework application that show how to show all customers, add and delete customer. This project is based on AWS EC2 instance, I installed Mysql and Jetty on my AWS EC2 instance. When I finished code change, package them into a WAR, upload WAR to under the "webapps" directory in Jetty on my AWS EC2 instance. When I restart Jetty on AWS EC2 instance, Jetty will launch my project automatically, and connect to Mysql installed also on AWS EC2 instance, after that you can use these Restful APIs exposed by this project.

## How to Run 

You can use the Tomcat embeded in Spring to run it. And this application is packaged as a war in github, so you can also upload it to under the "webapps" directory of your own Web Server like Jetty / Tomcat.

* Clone this repository 
* You can build the project and run the tests
* Once successfully built, you can run the service
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2020-03-31 15:28:33.793  INFO 21920 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-03-31 15:28:33.986  INFO 21920 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-03-31 15:28:33.989  INFO 21920 --- [           main] com.hinkmond.finalproj.JDBCApplication   : Started JDBCApplication in 3.596 seconds (JVM running for 4.259)
```

## About the Service

The service is just a simple customer management REST service. It uses the Mysql to store the data. If your database connection properties work, you can call some REST endpoints defined in ```com.hinkmond.finalproj.JDBCController``` on **port 8080**. (see below)

You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.
 
Here is what this little application demonstrates: 

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 9): No need to install a container separately on the host just run using the ``java -jar`` command
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* *Spring Data* Integration with just a few lines of configuration and familiar annotations. 

Here are some endpoints you can call:
```
http://localhost:8080/printAllCustomers
http://localhost:8080/addCustomer
http://localhost:8080/deleteCustomer
```

### Show All Customers

```
http://localhost:8080/printAllCustomers

Response: HTTP 200
Content: a list of customers 
```

### Add Customer

```
http://localhost:8080/addCustomer

POST /addCustomer
Accept: application/json
Content-Type: application/json

{
"first_name" : "David",
"last_name" : "Smith",
"addr" : "3820 Hollo Dr, San Jose",
"email" : "David_Smith@gmail.com"
}

RESPONSE: HTTP 200 
Rows updated: 1(base)
```
### Delete Customer

```
http://localhost:8080/deleteCustomer

POST /deleteCustomer
Accept: application/json
Content-Type: application/json

{
"first_name" : "David",
"last_name" : "Smith",
}

RESPONSE: HTTP 200 
Rows updated: 1(base)
```

## Mysql

Here is the sql statements we are using to create database and tables for this project for your reference.

### Create Database "credit_card_db"
```
CREATE DATABASE credit_card_db;
```

### Create table "customer"
```
CREATE TABLE IF NOT EXISTS customer (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    addr VARCHAR(255),
    phone VARCHAR(15),
    email VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```


