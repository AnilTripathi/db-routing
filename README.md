# Database Routing Demo

This project is a demo application for database routing using Spring Boot. It demonstrates how to configure multiple data sources and route database operations to different data sources based on certain conditions.

## Prerequisites

- Java 17
- Maven 3.9.9
- MySQL

## Environment Variables

The following environment variables need to be set for the application to run:

- `username`: The username for the database.
- `password`: The password for the database.
- `spring.datasource.url`: The JDBC URL for the read-write data source.
- `spring.datasource.readonly.url`: The JDBC URL for the read-only data source.

## Running the Application

1. Clone the repository:

```sh
git clone <repository-url>
cd dbrouting-demo
```
