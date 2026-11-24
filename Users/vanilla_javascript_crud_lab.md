# Practical Lab: Full-Stack CRUD Application

**Level:** Beginner -- Intermediate\
**Estimated duration:** 3 hours\
**Backend:** Java 17 + Spring Boot\
**Frontend:** HTML, CSS and Vanilla JavaScript\
**Database:** PostgreSQL\
**Build tool:** Maven

## Project overview

Continue from yesterday's User Registration project. Extend it into a
fully functional CRUD application using Vanilla JavaScript to
communicate with the existing Spring Boot REST API.

The application must allow users to create, retrieve, update and delete
user records stored in PostgreSQL.

## 1. Learning objectives

By the end of this practical, learners should be able to:

1.  Explain how a frontend communicates with a backend.
2.  Use JavaScript `fetch()` to consume REST APIs.
3.  Implement the HTTP methods POST, GET, PUT and DELETE.
4.  Display database records dynamically.
5.  Update and delete existing records.
6.  Handle API responses, validation and errors.
7.  Push a completed project to GitHub.

## 2. Project configuration

  Component           Technology / setting
  ------------------- --------------------------------
  Backend             Java 17 + Spring Boot
  Frontend            HTML, CSS, Vanilla JavaScript
  Database            PostgreSQL
  Database name       `vanilla_form_db`
  Build tool          Maven
  Backend port        `8080`
  Frontend location   Spring Boot `static` resources

Use the database and project configuration from yesterday. If your
existing database or package names differ, keep using your existing
names rather than creating unnecessary duplicates.

## 3. Application requirements

Manage users with these fields:

  Field   Data type   Requirement
  ------- ----------- ---------------------------------
  ID      Long        Automatically generated
  Name    String      Required; maximum 50 characters
  Email   String      Required; unique

The application must provide:

-   A form to register a user.
-   A table showing all registered users.
-   An Edit button for every user.
-   A Delete button for every user.
-   A form that can be used for both creating and updating users.
-   Clear success and error messages.

## 4. Suggested project structure

Keep the architecture from yesterday's project. A suggested structure
is:

``` text
src/main/java/com/example/vanillaform/
├── controller/
│   └── UserController.java
├── service/
│   └── UserService.java
├── repository/
│   └── UserRepository.java
├── entity/
│   └── User.java
└── dto/
    └── UserDTO.java

src/main/resources/
├── static/
│   ├── index.html
│   ├── style.css
│   └── script.js
└── application.properties
```

**Important:** Do not put database logic directly inside the Controller.

-   **Controller:** Receives HTTP requests and returns HTTP responses.
-   **Service:** Contains business logic.
-   **Repository:** Communicates with PostgreSQL.
-   **Entity:** Represents the database table.
-   **DTO:** Transfers data between the frontend and backend.

## 5. CRUD implementation tasks

### Task 1: GET -- Retrieve all users

**Time:** 30 minutes\
**HTTP method:** GET\
**Endpoint:** `GET http://localhost:8080/api/users`

Instructions:

1.  Create a JavaScript function called `getUsers()`.
2.  Use `fetch()` to retrieve users from the backend.
3.  Convert the response into JSON.
4.  Dynamically display the users inside an HTML table.
5.  Display each user's ID, name and email.
6.  Refresh the table whenever a user is successfully registered,
    updated or deleted.

**Expected outcome:** Users stored in PostgreSQL appear on the frontend
without manually adding table rows.

### Task 2: POST -- Register a new user

**Time:** 30 minutes\
**HTTP method:** POST\
**Endpoint:** `POST http://localhost:8080/api/users`

Instructions:

1.  Create a registration form with name and email fields.
2.  Capture the values using JavaScript.
3.  Use `fetch()` to send the information to Spring Boot.
4.  Set the request method to `POST`.
5.  Set the request header to `Content-Type: application/json`.
6.  Convert the form data into JSON using `JSON.stringify()`.
7.  Display a success or error message.
8.  Refresh the users table after successful registration.

Example request body:

``` json
{
  "name": "John Smith",
  "email": "john@gmail.com"
}
```

**Expected outcome:** A new user is saved in PostgreSQL and displayed in
the table.

### Task 3: PUT -- Update an existing user

**Time:** 40 minutes\
**HTTP method:** PUT\
**Endpoint:** `PUT http://localhost:8080/api/users/{id}`

Instructions:

1.  Add an Edit button to every user record.
2.  When Edit is clicked, retrieve the selected user's information.
3.  Populate the form with the existing information.
4.  Allow the user to modify their name and email.
5.  Send the updated information to the backend using `PUT`.
6.  Include the selected user's ID in the URL.
7.  Refresh the table after a successful update.

Example request body:

``` json
{
  "name": "John Updated",
  "email": "john.updated@gmail.com"
}
```

**Expected outcome:** The existing record is updated in PostgreSQL
instead of creating a second record.

### Task 4: DELETE -- Remove a user

**Time:** 30 minutes\
**HTTP method:** DELETE\
**Endpoint:** `DELETE http://localhost:8080/api/users/{id}`

Instructions:

1.  Add a Delete button to each table row.
2.  Ask the user to confirm before deleting.
3.  Send a `DELETE` request to Spring Boot.
4.  Include the selected user's ID in the URL.
5.  Display a success or error message.
6.  Refresh the table after successful deletion.

**Expected outcome:** The selected user is removed from PostgreSQL and
disappears from the table.

## 6. Required API endpoints

  Method   Endpoint            Function
  -------- ------------------- --------------------
  POST     `/api/users`        Register a user
  GET      `/api/users`        Retrieve all users
  GET      `/api/users/{id}`   Retrieve one user
  PUT      `/api/users/{id}`   Update a user
  DELETE   `/api/users/{id}`   Delete a user

The GET-by-ID endpoint is an additional requirement to help learners
understand REST API design.

## 7. Time allocation

  Activity                                                        Time
  ---------------------------------------- ---------------------------
  Project preparation and backend review                    15 minutes
  GET -- Retrieve users                                     30 minutes
  POST -- Register users                                    30 minutes
  PUT -- Update users                                       40 minutes
  DELETE -- Remove users                                    30 minutes
  Testing and debugging                                     20 minutes
  GitHub submission                                         15 minutes
  **Total**                                  **180 minutes (3 hours)**

## 8. Testing requirements

Before submitting, test every scenario:

-   [ ] Register a new user successfully.
-   [ ] Retrieve all registered users.
-   [ ] Retrieve one user using their ID.
-   [ ] Update an existing user's name.
-   [ ] Update an existing user's email.
-   [ ] Delete an existing user.
-   [ ] Attempt to register an email that already exists.
-   [ ] Attempt to update a user that does not exist.
-   [ ] Refresh the browser and confirm that saved records remain
    visible.

Handle unsuccessful responses appropriately. Do not assume every API
request succeeds. Check the HTTP response status and show a useful
message to the user.

## 9. Git and GitHub submission instructions

**Time:** 15 minutes

### Step 1: Initialise Git

Open a terminal inside the project directory:

``` bash
git init
```

### Step 2: Check changed files

``` bash
git status
```

### Step 3: Add project files

``` bash
git add .
```

### Step 4: Commit changes

``` bash
git commit -m "Complete vanilla JavaScript CRUD lab"
```

### Step 5: Create a GitHub repository

1.  Open [GitHub](https://github.com).
2.  Create a new repository.
3.  Use the repository name `vanilla-js-crud-lab`.
4.  Set the repository to Public, if permitted by your instructor.
5.  If your local project already has a README and Git history, do not
    initialise the remote repository with another README.

### Step 6: Connect the local project to GitHub

Replace `YOUR_USERNAME` with your GitHub username:

``` bash
git branch -M main

git remote add origin https://github.com/YOUR_USERNAME/vanilla-js-crud-lab.git

git push -u origin main
```

If an `origin` remote already exists, do not add it again. Check it
with:

``` bash
git remote -v
```

If necessary, update the existing remote URL:

``` bash
git remote set-url origin https://github.com/YOUR_USERNAME/vanilla-js-crud-lab.git
```

### Step 7: Verify submission

Open your GitHub repository and confirm that the source files and README
have been uploaded.

**Security reminder:** Never commit database passwords, API keys, tokens
or other secrets. Keep private configuration out of Git using
`.gitignore` and environment variables.

## 10. Submission requirements

Each learner must submit:

1.  The GitHub repository URL.
2.  A working application.
3.  Functional POST, GET, PUT and DELETE operations.
4.  PostgreSQL database integration.
5.  A `README.md` containing:
    -   Project description.
    -   Technologies used.
    -   Installation instructions.
    -   How to run the application.
    -   API endpoint documentation.

## 11. Assessment rubric

  Assessment area                         Marks
  ----------------------------------- ---------
  GET -- Retrieve users                      15
  POST -- Register users                     15
  PUT -- Update users                        20
  DELETE -- Remove users                     20
  Frontend functionality and design          10
  Error handling and validation              10
  GitHub submission and README               10
  **Total**                             **100**

## 12. Bonus challenge: Search users

For learners who finish early, add a search feature that allows users to
search by name or email.

Requirements:

-   Add a search input.
-   Listen for the JavaScript `input` event.
-   Filter the displayed users.
-   Do not reload the entire application.
-   Display a message when no matching users are found.

## 13. Rules for learners

-   Use Vanilla JavaScript only.
-   Do not use React, Angular or jQuery.
-   Do not hardcode database records in JavaScript.
-   All CRUD operations must communicate with the Spring Boot backend.
-   Follow the existing Controller → Service → Repository architecture.
-   Validate user input and handle API errors.
-   Commit and push your work to GitHub.

## Final objective

Build a working full-stack User Management System using yesterday's
project. Demonstrate that all four HTTP methods---POST, GET, PUT and
DELETE---communicate correctly with the Spring Boot backend and
PostgreSQL database.
