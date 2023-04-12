# Read Me document
* The application was built with spring-boot using Java version 8.
* It encapsulates all aspects of the assessment as requested:
i. RESTFUL API's for transaction statistics.
ii. Algorithims - This can be found in the AlgorithmSolutions.class (com.serbit.transaction.demo.algorithim)

* units tests have been written for both parts of the assessment; while integration tests were written for just the 
  RESTFUL API'S section.
  
* To run the application:
i. mvn clean package
ii. mvn spring-boot:run

* Swagger documentation has been included to make testing easier. So after starting the applicaiton, 
  navigate to http://localhost:8080/swagger-ui.html to test the API's. You could as well use postman.
  
* To create a docker image of the application, run the following commands:
i. mvn clean package
ii. docker build -t seerbit-transaction-image .


