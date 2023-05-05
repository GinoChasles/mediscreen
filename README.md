# mediscreen

## Technology and Prerequisites
JAVA 11 JDk
Springboot
Mysql
MongoDB
Maven
Docker

## Installing
Install Java: - https://www.oracle.com/fr/java/technologies/javase-downloads.html

Install Maven - https://maven.apache.org/install.html

Install MySql: - https://dev.mysql.com/downloads/mysql

Intall MongoDB: - https://docs.mongodb.com/manual/administration/install-community/

Install Docker: - https://www.docker.com/products/docker-desktop

## Executing

need to compile the three microservices with command "mvn clean install -DskipTests" to build project's jar (in each directory)

In main directory (with docker-compose.yml file) use "docker-compose up"

## Infrastructure

- patient microservice : 8081 
  - REST service, implements CRUD methods for patient with MySQL database
- note microservice : 8082 
  - REST service, implements CRUD methods for patient's notes with MongoDB database
- assess microservice : 8080
  - REST service for generate patient's assessment to anticipate the level of risk that a patient will develop diabetes. Using FeignClient to communicate with patient and note microservices 
- front : 3000
  - UI with Flutter



## Testing

![patient microservice test](https://github.com/GINOCHASLES/mediscreen/blob/master/patient/lib/patienttest.png?raw=true)

![notes microservice test](https://github.com/GINOCHASLES/mediscreen/blob/master/notes/lib/notetest.png?raw=true)

![assess microservice test](https://github.com/GINOCHASLES/mediscreen/blob/master/assess/lib/assesstest.png?raw=true)
