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
	* **Always Use Set not List to map the associations** -
		* Hibernate deals with @ManyToMany relationships as two unidirectional @OneToMany associations.
		* Each association relies on a foreign key stored in the join table.
		* Therefore, removal of an entity results in deleting all join entries from the join table and reinserting them to reflect the memory content (the current Persistence Context content).
		* If a ```List``` is used to map the association, while removing an author with a particular ID, 3 SQL statements are triggered for the join table:
		```sql
		DELETE FROM book_author_list
		WHERE book_id = ?
		Binding: [1]
	
		INSERT INTO book_author_list (book_id, author_id)
		VALUES (?, ?)
		Binding: [1, 1]
		
		INSERT INTO book_author_list (book_id, author_id)
		VALUES (?, ?)
		Binding: [1, 3]
		
		//and all other existing associations in join table.
		```
		* On the other hand, if a ```Set``` is used to map the association, while removing a book with a particular ID, only 1 SQL is triggered:
		```sql
		DELETE FROM book_author_set
		WHERE book_id = ?
		AND author_id = ?
		Binding: [1, 2]
		```
		* **Therefore, when we use ```Set``` to model the association, it results in performance improvements, specially while removing entities**.
		* **To order the results of a resultset of entities**, we need the ```@OrderBy``` annotation (because we use a ```Set``` to model the association, and a ```Set``` does not maintain the order of insertion of items into it.
		* Using ```@OrderBy``` with ```HashSet``` does not, however, maintain the order of the loaded/fetched ```Set``` in the transient state. To maintain the order in the transient state as well, we need the ```LinkedHashSet``` collection.
		
	* **Keep Both Sides of the Association in Sync** - both sides of the association can be kept in sync via helper methods added on the both the entity objects. For example, in the case of Book & Author entities, and assuming that Book is the owner of the relationship, helper methods to remove one particular author, or all authors, can be implemented as follows:
	 ```java
	  public void removeAuthor(Author author) {
	  	this.authors.remove(author);
		author.getBooks().remove(this);
	  }
	  public void removeAuthors() {
		Iterator<Author> iterator = this.authors.iterator();
		while (iterator.hasNext()) {
			Author author = iterator.next();
			author.getBooks().remove(this);
			iterator.remove();
		}
	  }
	 ```
	* **Avoid ```CascadeType.ALL``` and ```CascadeType.REMOVE```** - In most cases, cascading removals is not a good practice. If it is required, we should reply explicitly on ```CascadeType.PERSIST``` and ```CascadeType.MERGE```. For example, we can define the association for the set of authors of a book as follows (on the owner side):
	```java
	  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	  private Set<Author> authors = new HashSet<>();
	```
	* **Set Up the Join Table** - As mentioned before, the junction/join table stores the foreign key relationships between the entities participating in the many-to-many association. This table can be set up on the owner side as follows:
	```java
	  @JoinTable(name = "book_author",
		joinColumns = @JoinColumn(name = "book_id"),
		inverseJoinColumns = @JoinColumn(name = "author_id"))
	```
	* **Lazy Fetching on Both Sides of the Association** - By default, the @ManyToMany association is lazy, and it should be kept that way.
	* **Properly override toString()** - When overriding toString(), we should account only for basic attributes fetched from the database for the entity. If associations are used, additional SQL statements will be triggered.

## Aspect Oriented Programming in Spring ##
-------------------------------------------
* Aspect Oriented Programming is a term that refers to a type of programming that aims to **increase modularity by allowing the separation of cross-cutting concerns**.
* **Cross-cutting Concern** - A cross-cutting concern is a functionality that is tangled with business code, which usually cannot be separated from the business logic, e.g. auditing, security, transaction management, caching.
* **In AOP, additional behavior is added to existing behavior when the application is compiled.** So cross-cutting concerns can be developed separately and mingled with the functionality at compile time. 
* **This is achieved by defining an advice containing code that will be executed in a location named join point specified by a pointcut**.

### AOP Terminology ###
- - - -
* **Aspect**: a class containing code specific to a cross-cutting concern. **A class declaration is recognized in Spring as an aspect if it is annotated with the ```@Aspect``` annotation**.
* **Weaving**: it refers to aspects being combined with other types of objects to create an **advised object**.
* 
