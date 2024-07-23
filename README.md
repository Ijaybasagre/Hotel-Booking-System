# Hotel Booking System

This project is a hotel booking system developed using Spring Boot, Spring Data JPA, and MySQL. It allows users to book hotel rooms, manage reservations, and view hotel details.

## Features

- **User Management**: Allows registration and authentication of users.
- **Hotel Listings**: Displays available hotels with details such as location, amenities, and room types.
- **Booking Management**: Users can search for hotels, select rooms, and make reservations.
- **Reservation Tracking**: Keeps track of user reservations and booking history.
- **Admin Dashboard**: Admins can manage hotels, rooms, and user bookings.

## Technologies Used

- **Spring Boot**: Framework for creating Spring-powered applications.
- **Spring Data JPA**: Simplifies data access layer implementation.
- **MySQL**: Relational database management system for storing application data.
- **Java 17**: Programming language used for backend development.

## Setup Instructions

To run this project locally, follow these steps:

1. **Clone the repository**:
   - git clone https://github.com/your/repository.git
2. **Navigate to the project directory**:
   - cd hotel-booking-system
3. **Configure MySQL database**:
- Create a MySQL database named `hotel_booking_system`.
- Update the database configuration in `application.yml`:
  ```
  spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hotel_booking_system
    username: your_username
    password: your_password
  ```

4. **Run the application**:
   - ./gradlew bootRun
5. **Access the application**:
Open your web browser and visit `http://localhost:8080`.

## Usage

- **User Registration**: Create a new user account to start booking hotels.
- **Hotel Search**: Browse available hotels by location and dates.
- **Booking a Room**: Select a hotel, choose room types, and make a reservation.
- **Admin Dashboard**: Access `/admin` to manage hotels, rooms, and user bookings.

