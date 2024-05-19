# Social Media

Social Media is a straightforward social networking website developed using Spring Boot for the backend and Vue.js for the frontend. It provides users with basic social networking functionalities.

## Installation and Usage
To run the application using Docker, follow these steps:

1. Build the application with Maven:
   ```
   mvn package -DskipTests -P prod
   ```
2. Start the Docker containers:
   ```
   docker-compose up
   ```

Once the application is running, you can access the frontend at [http://localhost:5173](http://localhost:5173) and the backend at [http://localhost:8080](http://localhost:8080).

The application comes with two initial user accounts for testing purposes:
- Username: 0912345678, Password: password
- Username: 0987654321, Password: password
