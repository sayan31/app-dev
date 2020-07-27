# Some useful resources
--------------------------
## Spring Transactional Management ##
-----------------------------------
* [Article about Spring's @Transactional annotation](https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth)
* Chapter 6 of **Spring Boot Persistence Best Practices: Optimize Java Persistence Performance in Spring Boot Applications** by Anghel Leonard
## Spring DTO projection uses ##
-----------------------------------
* **Spring Boot Persistence Best Practices: Optimize Java Persistence Performance in Spring Boot Applications** by Anghel Leonard - Chapter 3, item 25
	* **For purely read-only data that will not be modified, use DTO/Spring Projection** to store the fetched raw data from an executed SQL query.
	* DTO relies on classes with constructor and getters/setters, while Spring projections rely on interfaces and automatically generated proxies.
	* When an entity has a significant number of attributes, we potentially need a bunch of read-only queries to fetch different subsets of attributes. It will be more practical to **define a single Spring projection that works for all read-only queries** executed against the entity.
		* To the above end, we define a Spring projection(class-based DTO) that contains getters to satisfy the heaviest query.	
		* However, using a DTO pair for each UI request/response is good(?) practice.

## Database Joins ##
-----------------------------------
<img src="https://learning.oreilly.com/library/view/spring-boot-persistence/9781484256268/images/487471_1_En_3_Chapter/487471_1_En_3_Fig15_HTML.jpg">
