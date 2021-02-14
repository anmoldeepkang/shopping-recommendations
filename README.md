# shopping-recommendations

## Overview
Items on Sale is a microservice which returns recommendations for items on sale based on the past orders of sale, wishlist of user & hot deals. The technologies used to implement the microservice include Java, Spring Boot & JPA.

## Endpoints
There is only one end-point in the application and it returns the list of products. The response codes as well as the endponits are as following:

| End point        | Status code   | Response  |
| ---------------- |:-------------:| ---------:|
| /recommendations/{username}      | 200 | Products for Hot deals, past orders & wish list |
| /recommendations/{username}      | 403 | The user trying to access the endpoint doesn't exist |

Instead of aggregating the product results from past orders, hot deals & wishlist, the results are output in the form of key-value pairs as hot deals, suggestions based on past orders & wishlist maybe three different sections on the user interface. Hence, segregating them in the response of the API is a better choice. A sample successfull response is as shown below:
{
    "wishList": [
        {
            "productId": "3",
            "name": "Toyota",
            "category": "Automobile"
        }
    ],
    "hotDeals": [
        {
            "productId": "1",
            "name": "Chair",
            "category": "Furniture"
        },
        {
            "productId": "2",
            "name": "Table",
            "category": "Furniture"
        }
    ],
    "pastOrders": [
        {
            "productId": "1",
            "name": "Chair",
            "category": "Furniture"
        },
        {
            "productId": "6",
            "name": "iPad",
            "category": "Electronics"
        }
    ]
}

## Database

The application uses an in-memory H2 database. Before the application starts, the backend tables are created and the data is inserted into them. There are 5 database tables as documented below:
| Table        | Description  |
| ---------------- |:-------------:|
| user      | This table contains all the users who have the access to the application. The passwords in the table are stored in encrypted format. |
| user_order      | This table contains all the orders that have been placed by the users in the past. |
| order_line | This table contains the each individual line for order. An order may consist of multiple products being purchased. Hence the information for those products is stored in this table. |
| product | This table contains the data of the items on sale. |
| wishlist | This table contains the data for the wishlist of users. |

The structure for each of the above tables in shown below:
user:

| Column        | Data Type           | Constraints  |
| ------------- |:-------------:| -------------------:|
| user_id      | VARCHAR(50)   | PRIMARY KEY |
| password      | VARCHAR(50)  |    |
| username | VARCHAR(50)    |     |

user_order:
| Column        | Data Type           | Constraints  |
| ------------- |:-------------:| -------------------:|
| order_id      | VARCHAR(50)   | PRIMARY KEY |
| user_id      | VARCHAR(50)  |  FOREIGN KEY User(user_id) |
| order_time | TIMESTAMP    |     |

order_line:
| Column        | Data Type           | Constraints  |
| ------------- |:-------------:| -------------------:|
| order_line_id      | VARCHAR(50)   | PRIMARY KEY |
| order_id      | VARCHAR(50)  |  FOREIGN KEY user_order(order_id) |
| product_id | VARCHAR(50)    |   FOREIGN KEY product(product_id)  |
| rating | INTEGER    |    |

product:
| Column        | Data Type           | Constraints  |
| ------------- |:-------------:| -------------------:|
| product_id      | VARCHAR(50)   | PRIMARY KEY |
| category      | VARCHAR(50)  |   |
| name | VARCHAR(50)    |     |

wishlist:
| Column        | Data Type           | Constraints  |
| ------------- |:-------------:| -------------------:|
| id      | VARCHAR(50)   | PRIMARY KEY |
| user_id      | VARCHAR(50)  | FOREIGN KEY user(user_id)  |
| product_id | VARCHAR(50)    |  FOREIGN KEY product(product_id)   |

## Authentication
The application has been protected by basic authentication. A sample user to try out the endpoint is: 
Username: anmoldeep/Password: anmoldeep123
A request to the API by an unauthenticated user gives 403 (Forbidden response) with the output as shown below:

{
    "status": 403,
    "timestamp": "14-02-2021T02:02:05",
    "error": "Forbidden"
}

## Cross origin restrictions:
The application's endpoints can only be accessed by https://shopping.rbc.com. An attempt to access from a different origin gives a 403 response with 'Invalid Cors request' text in response.

## Steps to test
Download the application & import in eclipse. Run the Application.java as a java application. Access the endpoint https://localhost:8080/recommendations/anmoldeep from postman while using the Authentication type as basic, username as anmoldeep & password as anmoldeep123. 

## Documentation
The documentation for the microservice has been done with Swagger. After running the application, the endpoint https://localhost:8080/swagger-ui.html gives the documentation related to the APIs. The API can be tried on this page by using the credentials mentioned earlier.

