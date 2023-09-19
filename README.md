# MicroServices-SpringBoot
This is a project of microservices using Springboot.
This project contains use of microservices at advanced level.
This project has various Microservices/Modules which are interconnected.
-----------------------------------------------------------------------------------------
It has following microservices : 
1. USER Microservice
2. RATING Microservice
3. HOTEL Microservice
4. API Gateway Microservice
5. SERVER CONFIG Microservice
6. SERVICE REGISTRY microservice
------------------------------------------------------------------------------------------
Technologies and Concepts used in the project :
----------------------------------------------
1. JAVA-8
2. Springboot
3. MYSQL
4. POSTGRESQL
5. MONGODB
7. Spring-Security ( Authorization and Authentication is done by OKTA)
8. Eureka-Server (To register the services)
9. Spring-Cloud (API-Gateway)
10. FEIGN-Client and HttpTemplate (For microservice intercommunication)
11. HIBERNATE
12. Circuit-Breaker, Retry, Rate-Limiters ( For fault-Tolerancy in microservices)
13. Jmeter (To test fault-tolerancy using rate limiters)

DESCRIPTION of what do these microservices do : 
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1. USER microservice contains userid , name, email, about, and data of RATINGS microservice . MYSQL database is used to store data
2. RATING microservice contains hotelid, hotelabout, hotellocation, hotelname and data of HOTEL microservice. POSTGRESQL database is used to store data
3. HOTEL microservice contains hotelid, userid , ratingid, feedback.
4. API Gateway microservice contains configuration for the gateway of the client requests to these microservices. This service uses spring-cloud gateway
5. SERVICE CONFIG microservice contains server configurations and common configurations used in the above microservices and basically configurations are stored in github.
6. SERVICE REGISTRY microservice contains eureka server configurations to register these services on eureka server.

EXAMPLE of Data you get from response when you fire get user :
-----------------------------------------------------
`{
    "userId": "038cc9d0-1b2a-4e7c-bd35-b52ce602e69c",
    "name": "Harsh Vyas",
    "email": "harsh@gmail.com",
    "about": "He is an Engineer",
    "ratings": [
        {
            "ratingId": "6507f2bf6391593d87e5c32c",
            "userId": "038cc9d0-1b2a-4e7c-bd35-b52ce602e69c",
            "hotelId": "8140c826-43e7-4157-8b63-e33a36c72787",
            "rating": 10,
            "feedback": "The best thing it is near to railway station",
            "hotel": {
                "id": "8140c826-43e7-4157-8b63-e33a36c72787",
                "name": "Sree om jyoti",
                "location": "somnath",
                "about": "It has best washrooms"
            }
        }
    ]
}
`

------------------------------------------------------------



