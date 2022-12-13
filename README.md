# University Management System

This project deals with university management system using H2 database presenting with its CRUD operations

- **Flow of the system**
    - Open the code on any editor (IntelliJ, VSCode, etc.)
    - For IntelliJ, run the code by clicking the play button
    - **Note:** While opening the project in other machine and get maven plugin or sync error, just ignore it
      and run the program.
    - Upon starting the application, the database gets initialized with preloaded degrees and majors
    - Upon starting, one will be able to see database operations queries printed on the console of the code editor.
    - The console is accessible through the link `http://localhost:2000/h2-console`
    - **Note:** On the console if you find the JDBC URL to be other than `jdbc:h2:mem:university`, kindly replace it with the mentioned one.
    - The degrees can be fetched using `SELECT * FROM DEGREE` on H2 console (url mentioned above)
    - For testing, Postman can be used, like `http://localhost:2000/api/student/1`


- **Project Configuration Description**
    - Runs on port `2000`. Can be configured in application.properties file.
    - Port can be configured in `application.properties`

- **Automation & Unit Tests**
    - For automation, Cucumber (BDD) has been used alongwith Selenium
    - For unit tests, JUnit tests have been written
    - For automation, first open terminal `frontend` folder and type `npm start` (make sure you have node installed). Then start the spring boot application.
    - Now, open a new terminal in root folder of the project and type `mvn clean install test surefire-report:report`, then after complete execution, several reports are generating indicating the performance of automation.


- **Project Working Description**
    - The degrees/majors already included into the database, from `data.sql`. New degrees/majors can also be created via the
      defined REST API
    - The project is completely implemented with frontend based on `React`, backend based on `Java/Spring Boot`, manual tests written using `Junit` and automation done using `Cucumber` and `Selenium`



- **APIs Description**
    - Implemented in student service
        - For creating student
            - `POST /api/student`
        - For deleting student based on ID
          - `DELETE /api/student/{id}`
        - For fetching student based on ID
            - `GET /api/student/{id}`
        - For fetching student list
            - `GET /api/student/list`
        - For updating student email (with email in request body)
            - `PUT /api/student/email/{id}`
        - For updating all the fields in a student entity
            - `PUT /api/student/{id}`

  - Implemented in professor service
      - For creating professor
          - `POST /api/professor`
      - For deleting professor based on ID
          - `DELETE /api/professor/{id}`
      - For fetching professor based on ID
          - `GET /api/professor/{id}`
      - For fetching professor list
          - `GET /api/professor/list`
      - For updating professor email (with email in request body)
          - `PUT /api/professor/email/{id}`
      - For updating all the fields in a student entity
          - `PUT /api/professor/{id}`

  - Implemented in registration service
    - For creating registration
        - `POST /api/registration`
    - For fetching registrations list
        - `GET /api/registration/list`

  - Implemented in course service
      - For fetching course list
          - `GET /api/course/list`
  - Implemented in degree service
      - For fetching degree list
          - `GET /api/degree/list`
  - Implemented in major service
      - For fetching major list
          - `GET /api/major/list`
