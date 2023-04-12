# TransactionStatistics

# A Seerbit Coding Assessment

#  How To Run The Project
Clone the project from this repo link

https://github.com/vee-loves-code/TransactionStatistics

# Clean, Run and Package a new Jar file using

mvn clean package

# Start up the docker container using
docker-compose up -d

# Explore the App via HTTP Rest Calls using Postman or other HTTP Client
 POST http://localhost:8080/transaction
  GET http://localhost:8080/statistics 
 DELETE http://localhost:8080/transaction 
 
# Stop up the docker container using
docker-compose down
