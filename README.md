# Spring Boot Application

A basic Spring Boot application with controllers and service classes.

## Project Structure

- **Controllers**: `UserController`, `ProductController`
  - Handle HTTP requests and responses
  - Route requests to appropriate service classes

- **Services**: `UserService`, `ProductService`, `OrderService`
  - Contain business logic
  - Perform operations on user, product, and order data

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Users
- `GET /api/users/{id}` - Get user info
- `POST /api/users?name=John` - Create a new user
- `GET /api/users` - Get all users

### Products
- `GET /api/products/{id}` - Get product details
- `POST /api/products?name=Laptop&price=999.99` - Add a new product
- `GET /api/products` - List all products
