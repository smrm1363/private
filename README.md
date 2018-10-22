# Energy consumption
In this sample project I used below technologies and tools:
Java 8 
Spring Boot
JPA - Hibernate
Spring data
H2 database : beacuse it is just a demo
Spring REST
Spring IOC
opencsv : for converting CSV files to DTO
Junit,
Mockito,
Spring integration test
MAVEN

Embedeb spring boot TomeCat.

About the implemented project, First of all, I would like to mention that I developed both the main task and the Bonus task are mentioned in the use case. Second, I assume all the mentioned assumptions in the use case are fulfilled, and I did not check them. For more explanation, I have developed a dynamic solution for checking validations, I mean we can add new validation by just developing a new validation logic in an inherited class from my ValidationRule, then add the class refrence in to application.properties file, this is a way to have a chain of validations.

I used some usefule design patterns such as Fatcory and Strategy, moreover I tried to make my codes reusable. In this project, I mad different type of classes such as entities, Repositories, Services(the main business logic), Rest coltrollers, DTO, Util, usefule Component and interfaces. Furthermore, I mad some important unit tests and integration tests, indeed, I mad integration test only for service beans, duo to they work with all other clases and database.

According to the context of the use case the REST APIs are described bellow:



http://localhost:8080/uploadProfileFraction  Method  : POST     Parameter:  add CSV file in body     KEY: file   VALUE: The CSV file for ProfileFraction

http://localhost:8080/deleteProfile          Method  : DELETE   parameter:  in URL   KEY: profileId  Value: profile id  example: A

http://localhost:8080/findProfile            Method  : GET      parameter:  in URL  KEY: profileId   Value: profile id  example: A

http://localhost:8080/insertProfile          Methos  : POST     Parameter:  add JSON object in body  KEY: profile   VALUE: The SON object for ProfileFraction

http://localhost:8080/insertProfileFromLocalFile         Methos  : POST     Parameter:  add String path of the local CSV  KEY: localFilePath   VALUE: a String contains path of local file  example: E:\profile.csv

--


http://localhost:8080/uploadMeterReading  Method  : POST     Parameter:  add CSV file in body     KEY: file   VALUE: The CSV file for MeterReading

http://localhost:8080/deleteMeter          Method  : DELETE   parameter:  in URL   KEY: meterId  Value: profile id  example: 0001

http://localhost:8080/findMeter           Method  : GET      parameter:  in URL  KEY: meterId   Value: profile id   example: 0001

http://localhost:8080/insertMeter      Methos  : POST     Parameter:  add JSON object in body  KEY: meter    VALUE: The JSON object for MeterReading

http://localhost:8080/insertMeterFromLocalFile         Methos  : POST     Parameter:  add String path of the local CSV  KEY: localFilePath   VALUE: a String contains path of local file  example: E:\meter.csv

http://localhost:8080/loadConsumption          Method  : GET      parameter:  in URL  KEY: meterId&month   Value: profile id  and moth  example: meterId=0001&month=DEC
