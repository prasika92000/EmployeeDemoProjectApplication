EmployeeDemoProjectApplication class, which is the main class of the project and it has the spring annotation @SpringBootApplication
In the pom.xml, I have included the following dependencies:
•	Spring Web: used to build REST APIs and handle HTTP requests like GET, POST, PUT, and DELETE.
•	Spring Data JPA: used for database interaction. 
•	MySQL Driver: used to connect the Spring Boot application with the MySQL database.
After that, I created an Employee entity class, where I defined all the required fields. This class represents the database table employee_table. Then, I created a DTO (Data Transfer Object). DTO is used to transfer data between layers. In real enterprise applications, DTO is used to convert UI data into Java objects and to send only required data to the frontend. Next, I created the Controller layer, which acts as the main entry point. All UI or client requests come to the controller. After that, I created the Service layer, where I defined an interface and its implementation class (ServiceImpl). In this layer, I created the method getMaxSalaryByDept(), which contains the logic to fetch the maximum salary for each department. For this logic, I used a native SQL query to directly interact with the database. I also optimized the query for performance by selecting only required columns instead of fetching all data. I used an inner join instead of left or right join to get accurate results efficiently. Then, I created the Repository layer, which extends JpaRepository. It provides additional built-in functionality for database operations like CRUD without writing much code.I also added exception handling created one custome class EmployeeNotFoundException, If employee data is not found, the application will not fail or throw a null pointer exception. Instead, it will return a proper custom exception, making the API more stable and secure.So overall, I have implemented a layered Spring Boot architecture that follows this flow: Controller → Service Layer → Repository → Database (MySQL)

Project Flow – 
The application starts from the EmployeeDemoProjectApplication main class, which boots the Spring Boot project using SpringApplication.run(). When the request send to the endpoint GET http://localhost:8080/api/employees/maxsalary/api, Spring Boot internally handles it using Spring Web’s request dispatcher mechanism It identifies the correct controller based on the URL mapping and forwards the request to the MyController class.
Inside MyController, the request is handled by the method getMaxSalaryByDept() mapped with @GetMapping("/maxsalary/api"). This controller method does not contain any business logic; it simply calls the service layer method employeeService.getMaxSalaryByDept().
The request then moves to the service layer, where the EmployeeServiceImpl class contains the actual business logic. In the getMaxSalaryByDept() method, an EntityManager is used to execute a native SQL query. This query first calculates the maximum salary for each department using a subquery with GROUP BY department and MAX(salary), and then performs an INNER JOIN with the employee_table to fetch the complete employee records who have the highest salary in each department. The query is executed using entityManager.createNativeQuery and the result is returned as a List<Object[]>.
After fetching the data from the database, the service layer checks if the result is empty. If no data is found, it throws a custom exception EmployeeNotFoundException to handle the error and avoid application failure.
Finally the result is returned back to the controller, which sends it as a response to the client. Spring Boot automatically converts the Java object (List<Object[]>) into JSON format using Jackson. 

Postman URL - GET http://localhost:8080/api/employees/max-salary
Output  - 
[
    [
        101,
        "Cash Mgmt",
        85000.0
    ],
    [
        102,
        "Lending",
        120000.0
    ],
    [
        103,
        "Sales",
        48000.0
    ]
]

