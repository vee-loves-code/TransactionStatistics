
* encapsulates all aspects of the assessment as requested:

i. RESTFUL API's for transaction statistics.
ii. Algorithims - This can be found in the AlgorithmSolutions.class 

* units tests have been written for both parts of the assessment; while integration tests were written for just the 
  RESTFUL API'S section.
  
* To run the application:
- mvn clean package
- mvn spring-boot:run

  
* To create a docker image of the application, run the following commands:
- mvn clean package
- docker build -t TransactionStatistics-image .


