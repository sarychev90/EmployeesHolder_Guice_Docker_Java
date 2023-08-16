# EmployeesHolder_Guice_Docker_Java

This is a Guice Framework REST service project. The application allows you to perform CRUD operations with employee entity in MySQL DB through REST API provided by service.

## What you need to run the application
1. Clone project from GitHub
2. Starting Docker on your machine
3. Run the command line from the parent folder of the project
4. Run the following command:

	docker-compose up
	
After launching the application, follow the link: http://localhost:8080/EmployeesHolder/employees to get the list of employees.

Also the following HTTP request methods supported:

GET http://localhost:8080/EmployeesHolder/employees -> get the list of employees;
GET http://localhost:8080/EmployeesHolder/employees/id/{id} -> get employee by id;
GET http://localhost:8080/EmployeesHolder/employees/name/{name} -> get the list of employees by name;
POST http://localhost:8080/EmployeesHolder/employees body: {"id": null, "name": "nameValue"} -> create new employee;
PUT http://localhost:8080/EmployeesHolder/employees body: {"id": idValue, "name": "newNameValue"} -> update employee;
DELETE http://localhost:8080/EmployeesHolder/employees/{id} -> delete employee by id.
