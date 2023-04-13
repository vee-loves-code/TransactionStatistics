# API Documentation for TransactionController:

# POST /transactions
This API is used to add a new transaction.

# Request Body:
{
"amount": "12.50",
"timestamp": "2023-04-12T11:22:33.456Z"
}

# Response:

HTTP Status: 201 CREATED
No Response Body

# GET /transactions
This API is used to fetch statistics of all transactions made in the last 60 seconds.

# Response:

HTTP Status: 200 OK
# Response Body:
{
"status": "SUCCESSFUL",
"message": "",
"data": {
"sum": "125.00",
"avg": "12.50",
"max": "25.00",
"min": "5.00",
"count": 10
}
}

# DELETE /transactions
This API is used to delete all transactions.

# Response:

HTTP Status: 204 NO CONTENT
No Response Body
Note:

All timestamps is in ISO 8601 format.

* units tests have been written for both parts of the assessment; while integration tests were written for just the
RESTFUL API'S section.

To run the application:
i. mvn clean package
ii. mvn spring-boot:run

# Attempt covers all parts of the assessment 
1. RESTFUL API's for transaction statistics.
2. Algorithims 
