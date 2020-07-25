# Some useful resources
--------------------------
## Spring Transactional Management ##
-----------------------------------
* [Article about Spring's @Transactional annotation](https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth)
* Chapter 6 of **Spring Boot Persistence Best Practices: Optimize Java Persistence Performance in Spring Boot Applications** by Anghel Leonard
## Spring DTO projection uses ##
-----------------------------------
* **Spring Boot Persistence Best Practices: Optimize Java Persistence Performance in Spring Boot Applications** by Anghel Leonard - Chapter 3, item 25
	* For purely read-only data that will not be modified, use DTO/Spring Projection to store the fetched raw data from an executed SQL query.
	* DTO relies on classes with constructor and getters/setters, while Spring projections rely on interfaces and automatically generated proxies.
	
