# Employee Management Application



## Overview

The **Employee Management Application** is designed to streamline the management of employee data and associated designations. It enables administrators to efficiently handle operations like adding, updating, and viewing employee and designation details. Built with a robust backend, it ensures seamless data integrity using foreign key constraints and efficient resource management through singleton classes.

## Features

### Employee Operations
- **Add Employee:** Easily add new employees with essential details.
- **Update Employee:** Modify existing employee records.
- **View Employee Details:** Access a detailed view of individual employee profiles.


![image](https://github.com/user-attachments/assets/f3559c8a-ed76-4956-a802-aef2f83f1b5c)




### Designation Management
- **Add Designation:** Add new designations with a unique code and title.
- **Update Designation:** Modify existing designation records.
- **View Designation Details:** Access detailed information about designations.
- **Foreign Key Integration:** Each employee is linked to a designation through a foreign key constraint, ensuring data consistency.


![image](https://github.com/user-attachments/assets/ccec1e98-e33b-4e0d-9d49-37d20689d5e7)
  

### Search & Filter
- **Search Employees:** Quickly search employees by name, ID, or designation code.
- **Filter by Department/Role/Designation:** Narrow down employee lists using advanced filters.

### Secure Authentication
- **Admin Login:** Only authorized admins can access the application.

### Analytics Dashboard
- **Employee & Designation Statistics:** View key metrics like total employees, designation distribution, and more.

## Technologies Used

- **Frontend:** React, HTML, CSS
- **Backend:** Java, Spring Boot
- **Database:** MySQL (with foreign key constraints)
- **Design Patterns:** Singleton classes for efficient resource management
- **Version Control:** Git, GitHub

## Local Installation Guide

### Prerequisites
Ensure you have the following installed:
- **Java JDK** (version 11 or higher)
- **Node.js** (version 22.12.0 or higher)
- **MySQL** (version 8.4 or higher)



### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/sudarshanPatidar/employee-management-app.git

2. Set up the backend:

    ```bash
    Copy code
    cd backend
    mvn install
    mvn spring-boot:run

3. Set up the frontend:

    ```bash
    Copy code
    cd ../frontend
    npm install
    npm start

4. Configure the database:

   - Set up a MySQL database and update the application.properties file in the backend with your credentials.
   - Ensure foreign key constraints are enabled and the schema includes relationships between employees and designations.

5. Ports

   - The backend will run on http://localhost:8080.
   - The frontend will run on http://localhost:3000.
   - Access the application: Open your browser and go to http://localhost:3000.

### Usage
  
  -Log in with your admin credentials.
  -Use the dashboard to manage employees and designations.
