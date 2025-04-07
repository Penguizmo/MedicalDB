# Medical Database Management System

## Overview
This project is a Medical Database Management System that allows users to manage information related to doctors, patients, drugs, prescriptions, visits, and insurance.

## Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- MySQL 5.7 or higher

## Setup Instructions

1. **Clone the repository:**
git clone https://github.com/yourusername/medicaldb.git cd medicaldb

2. **Configure the database:**
   - Create a MySQL database named `healthinsurancedb`.
   - Update the database connection details in `src/main/java/com/medicaldb/util/DatabaseConnection.java` if necessary.

3. **Build the project:**
mvn clean install

4. **Run the application:**
mvn exec:java -Dexec.mainClass="com.medicaldb.Main"

## Project Structure
- `src/main/java/com/medicaldb/dao`: Data Access Objects (DAOs) for interacting with the database.
- `src/main/java/com/medicaldb/model`: Model classes representing the entities in the database.
- `src/main/java/com/medicaldb/util`: Utility classes, including database connection management.
- `src/main/resources`: Configuration files and resources.

License
This project is licensed under the MIT License.