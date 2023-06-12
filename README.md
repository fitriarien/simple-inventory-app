# Simple Inventory Application

Build API (CRUD) + Unit Test

### Features

- Register
- Login
- Logout
- Create Product
- Get a Product
- Get All Product (pageable)
- Update Product
- Delete Product
- Search a Product (pageable)

### Technology

- Java
- Spring Boot
- Spring Security
- JSON Web Token
- Spring Boot Test
- MockMVC
- MySQL
- Postman API

## How to
### Import SQL Dump
1. Create a new database on server
2. Navigate to SQL dump file
3. Import the SQL dump file using this command
```
mysql -u username -p database_name < inventorydb.sql
```
4. Enter MySQL user password

### Run App with IDE
1. Open simple-inventory-app with IDE (ex: IntelliJ IDEA)
2. Open application.properties file and fill in the spring.datasource.password column
3. Run SimpleInventoryApplication.java
4. Press Ctrl(+Fn)+F2 to terminate 

### Run App with Maven command
1. Open cmd with the directory path of project
2. Run with this command
```
mvn spring-boot:run
```
3. Press Ctrl+C - Y - Enter to terminate