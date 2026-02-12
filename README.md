# CourseApp - Spring Boot MVC Project

A Spring Boot web application using Thymeleaf for templating, Bootstrap for a responsive UI, and Apache Derby as the data store.

🚀 Features

Persistent Navbar: Bootstrap-based navigation that stays at the top while scrolling.

Thymeleaf Fragments: Reusable components for headers, footers, and menus.

Embedded Database: Powered by Apache Derby (no external database installation required).

Lombok Integration: Clean code with automated Boilerplate generation.

## 🛠 Prerequisites

<ul>
<li> Java 17 or higher </li>
<li> Maven 3.6+
<li> IDE: Spring Tools for Eclipse or IntelliJ IDEA
</ul>

## ⚙️ Configuration

The application uses an embedded Derby database. The configuration in src/main/resources/application.properties is set as follows:

Properties

<code>
### Database Configuration

spring.datasource.url=jdbc:derby:memory:coursedb;create=true
spring.datasource.driverClassName=org.apache.derby.jdbc.EmbeddedDriver
spring.jpa.database-platform=org.hibernate.dialect.DerbyTenSevenDialect

# View Derby Console (Optional)

spring.h2.console.enabled=true
</code>

## 🏗 Build and Run

<ol>
<li> Clone the repository:

Bash
<code>
git clone https://github.com/<repo>
cd <repo-folder>
</code>

</li>

<li>Build with Maven:

Bash
<code>
mvn clean install
</code>

</li>

<li>Run the application:

Bash
<code>
mvn spring-boot:run
</code>

</li>

The app will be available at: http://localhost:8060
(Appilication Port is set in resources/application.properties )

## 📁 Project Structure

<code>
src/main/java/.../
├── controller/     # Web Request Handlers
├── model/          # Entities
├── repository/     # Spring Data JPA Repositories
└── service/        # Business Logic
</code>

<code>
src/main/resources/
├── templates/
│   ├── fragments/  # Shared Navbar and Footer
│   ├── home.html   # Main Landing Page
│   ├── course_list.html # course-list View
│   ├── course_new.html
│   ├── course_edit.html   
│   ├── assignment_list.html
│   ├── assignment_new.html
│   ├── assignment_edit.html
│   ├── user_list.html.html
│   ├── user_new.html.html
│   └── user_edit.html
└── application.properties
</code>
