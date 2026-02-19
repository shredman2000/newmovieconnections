# Movie Connections - Full Stack Web Application

A complete, containerized web application for managing movie connections and relationships. Built with Spring Boot backend, React + Vite frontend, PostgreSQL database, and orchestrated with Docker Compose.

## 🏗️ Architecture

- **Backend**: Spring Boot 4.0.3 with Spring Data JPA
- **Frontend**: React 18 with Vite for hot module replacement
- **Database**: PostgreSQL 16
- **Containerization**: Docker & Docker Compose
- **API**: RESTful with CORS support

## 📋 Features

- Browse and search movies by title, genre, director, or release year
- Create, read, update, and delete movies
- Full-stack CRUD operations
- Real-time hot reload during development
- Database persistence with PostgreSQL
- Modern UI with responsive design
- Production-ready Docker containers

## 🚀 Quick Start

### Prerequisites

- Docker and Docker Compose installed
- OR Node.js 18+ and Maven 3.9+ (for local development)

### Using Docker Compose (Recommended)

1. Clone the repository
2. Navigate to the project root
3. Run the application:

```bash
docker-compose up -d
```

The application will be available at:
- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8080/api
- **Database**: localhost:5432

### Local Development (Without Docker)

#### Backend Setup

1. Install and start PostgreSQL locally
2. Update `src/main/resources/application.properties` with your database credentials
3. Build and run:

```bash
mvn clean install
mvn spring-boot:run
```

Backend will be available at: http://localhost:8080/api

#### Frontend Setup

1. Navigate to the frontend directory:

```bash
cd frontend
```

2. Install dependencies:

```bash
npm install
```

3. Start the development server:

```bash
npm run dev
```

Frontend will be available at: http://localhost:5173

## 📁 Project Structure

```
newmovieconnections/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/movieconnections/app/
│   │   │       ├── MovieconnectionsApplication.java (main)
│   │   │       ├── config/CorsConfig.java
│   │   │       ├── controller/MovieController.java
│   │   │       ├── entity/Movie.java
│   │   │       └── repository/MovieRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   │   ├── MovieList.jsx
│   │   │   ├── MovieForm.jsx
│   │   │   └── *.css
│   │   ├── services/
│   │   │   └── movieService.js
│   │   ├── App.jsx
│   │   ├── main.jsx
│   │   └── index.css
│   ├── package.json
│   ├── vite.config.js
│   ├── index.html
│   └── Dockerfile
├── pom.xml
├── Dockerfile.backend
├── compose.yaml
└── README.md
```

## 🔧 API Endpoints

### Movies

- `GET /movies` - Get all movies
- `GET /movies/{id}` - Get movie by ID
- `POST /movies` - Create new movie
- `PUT /movies/{id}` - Update movie
- `DELETE /movies/{id}` - Delete movie

### Search

- `GET /movies/search/title?title=<query>` - Search by title
- `GET /movies/search/genre?genre=<query>` - Search by genre
- `GET /movies/search/director?director=<query>` - Search by director
- `GET /movies/search/year?year=<query>` - Search by release year

## 🗄️ Database Schema

### Movies Table

```sql
CREATE TABLE movies (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_year INTEGER NOT NULL,
    genre VARCHAR(255) NOT NULL,
    director_name VARCHAR(255),
    imdb_rating DOUBLE PRECISION
);
```

## 🔌 Environment Variables

### Backend (application.properties)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/movieconnections
spring.datasource.username=movieuser
spring.datasource.password=moviepass
server.port=8080
```

### Frontend (.env)

```
VITE_API_URL=http://localhost:8080/api
```

## 📦 Building for Production

### Backend

```bash
mvn clean package
docker build -f Dockerfile.backend -t movieconnections-backend:latest .
```

### Frontend

```bash
cd frontend
npm run build
docker build -t movieconnections-frontend:latest .
```

### All Services

```bash
docker-compose build
docker-compose up -d
```

## 🛑 Stopping the Application

```bash
# Stop containers
docker-compose down

# Remove containers and volumes
docker-compose down -v
```

## 🐛 Troubleshooting

### Backend fails to connect to database

- Check PostgreSQL is running and healthy: `docker-compose ps`
- Verify credentials in `application.properties`
- Check database logs: `docker-compose logs postgres`

### Frontend cannot reach backend

- Ensure backend is running: `docker-compose logs backend`
- Check CORS configuration is correct
- Verify `VITE_API_URL` environment variable

### Port already in use

- Change ports in `compose.yaml` or kill the blocking process
- For macOS: `lsof -i :5173` then `kill -9 <PID>`
- For Windows: `netstat -ano | findstr :5173` then `taskkill /PID <PID> /F`

## 📝 Development Workflow

1. Backend changes: Files in `src/main` auto-compile with devtools
2. Frontend changes: Vite hot reloads on file save
3. Database changes: Schema updates automatically via Hibernate

## 🔐 Security Notes

- Default database credentials are for development only
- Change credentials in production
- Add authentication with Spring Security if needed
- Enable HTTPS in production
- Restrict CORS origins to your domain

## 📚 Technologies Used

- **Spring Boot 4.0.3** - Java framework for building REST APIs
- **Spring Data JPA** - Data access layer
- **PostgreSQL 16** - Relational database
- **React 18** - UI library
- **Vite 5** - Modern frontend build tool
- **Axios** - HTTP client
- **Docker & Docker Compose** - Containerization

## 📄 License

This project is provided as-is for demonstration purposes.

## 🤝 Support

For issues or questions, check the troubleshooting section or review Docker logs:

```bash
docker-compose logs -f
```

---

**Happy movie connecting!** 🎬
