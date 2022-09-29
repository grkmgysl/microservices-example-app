# Microservices REST API Application

In this application I used microservices architecture to create a basic REST api

| Methods | Urls           | RequestBody    | Actions                                       |
|---------|----------------|----------------|-----------------------------------------------|
| POST    | /api/product   | ProductRequest | Create product info                           |
| GET     | /api/product   | --             | Get all products                              |
| POST    | /api/order     | OrderRequest   | Create order if product is in stock           |
| GET     | /api/inventory | skuCode list   | Checks products whether it is in stock or not |


### Project Architecture:

![arch](./githubPictures/architecture.png "Project architecture")

### Used Technologies:

- Java 
- Spring boot
- Microservices architecture
- Controller - Service - Repository architecture
- Netflix Eureka for Service Discovery
- Api Gateway
- MongoDB, MySQL
- Testcontainers

#### Services:

- Product-service: For storing product information
- Order-service: For placing orders
- Inventory-service: For checking the inventory 

#### Tests:

There are some test cases using Testcontainers inside 

- ProductServiceApplicationTest: Testing for POST and GET requests
- OrderServiceApplicationTest: Testing for POST request (this test case covers the InventoryService GET Request indirectly)

