The app was developed using Java 17 records, Java 15 text blocks, and the pattern data mapper

# Adventure Book API

> A project designed to upload and read books interactively


## 📌 Description
It lets us build a collection of books, read them, and save our progress. 
It’s interactive, like a game, where the choices we make determine whether we finish the book or lose a life in the process.

## 🚀 Technologies

Spring Boot + JPA + PostgreSQL + OpenAPI
- Java 17
- Maven
- Spring Boot 4.x
- Spring Data JPA (spring-boot-starter-data-jpa)
- Spring Web MVC (spring-boot-starter-webmvc)
- Spring Security
- Spring Boot Docker Compose
- Serialization Jackson (jackson-core, jackson-databind, jackson-annotations)
- Lombok
- MapStruct
- Maven Compiler Plugin(Lombok + MapStruct)


```bash
### Open API / Swagger documentation
the following is the link to the api documentation, generated with open API

http://localhost:8080/adventure-book/api/swagger-ui/index.html#


ENDPOINTS

GetbookById
http://localhost:8080/adventure-book/api/books/1  

GetSection
http://localhost:8080/adventure-book/api/section/2/section/100

GetBookByParams
http://localhost:8080/adventure-book/api/books?category=TERROR

Delete category
http://localhost:8080/adventure-book/api/books/1/categories/terror

Add category
http://localhost:8080/adventure-book/api/books/1/categories

Save Book
http://localhost:8080/adventure-book/api/books/save

StartGame
http://localhost:8080/adventure-book/api/game/start

ChooseOption
http://localhost:8080/adventure-book/api/game/1/choose/666

GetGameById
http://localhost:8080/adventure-book/api/game/2


