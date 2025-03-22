# Instructions to Run Popcorn Palace Backend

## Prerequisites
Before starting, ensure you have the following installed on your system:

- Docker
- Maven
- Java 21

---

## Steps

1. **Clone the Repository**
   ```bash
   git clone <repo-url>
   cd <repo-folder>
   ```

2. **Start PostgreSQL Database**
   ```bash
   docker-compose up -d
   ```

3. **Build the Project**
   ```bash
   ./mvnw clean install
   ```

4. **Run the Project**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Run Tests**
   ```bash
   ./mvnw test
   ```

