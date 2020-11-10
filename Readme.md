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

## Designing Entity Associations ##
-----------------------------------
### Many-to-many associations ###
- - - -
* We will assume that two entities, Author and Book, are involved in a **bidirectional lazy @ManyToMany association** (a book can have multiple authors, an author might have written multiple books).
	* This can be navigated from both sides, therefore, both sides can be parents (parent-side). Since both are parents, none of them will hold a foreign key.
	* There will be 2 foreign keys stored in a **junction or join table**. This table is hidden and plays the child-side role.
* The best practices for developing an association of this kind rests on 5 principles:
	* **Choose the Owner of the Relationship** - We should choose an owner side of the relationship and the inverse side. Changes to the database are only propagated from the owner side of the relationship. In this example case, assuming Book as the owner of the relationship, we can define the inverse side as follows: 
	 ```java
	  @ManyToMany(mappedBy = "authors")
	  private Set<Book> books = new HashSet<>();
	 ```
	* **Always Use Set not List** -
		* 
	* **Keep Both Sides of the Association in Sync** - both sides of the association can be kept in sync via helper methods added on the both the entity objects. For example, in the case of Book & Author entities, and assuming that Book is the owner of the relationship, helper methods to remove one particular author, or all authors, can be implemented as follows:
	 ```java
	  public void removeBook(Book book) {
	  	this.books.remove(book);
		book.getAuthors().remove(this);
	  }
	  public void removeBooks() {
		Iterator<Book> iterator = this.books.iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			book.getAuthors().remove(this);
			iterator.remove();
		}
	  }
	 ```
	* **Avoid ```CascadeType.ALL``` and ```CascadeType.REMOVE```** - 
