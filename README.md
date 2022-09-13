# Spring H2 University Database Example

This project deals with university database using H2 database presenting as an example with its CRUD operations

- **Flow of the system**
    - Open the code on any editor (IntelliJ, VSCode, etc.)
    - For IntelliJ, run the code by clicking the play button
    - **Note:** While opening the project in other machine and get maven plugin or sync error, just ignore it
      and run the program.
    - Upon starting the application, the database gets initialized with preloaded degrees and majors
    - Upon starting, one will be able to see database operations queries printed on the console of the code editor.
    - The console is accessible through the link `http://localhost:3000/h2-console`
    - **Note:** On the console if you find the JDBC URL to be other than `jdbc:h2:mem:university`, kindly replace it with the mentioned one.
    - The degrees can be fetched using `SELECT * FROM DEGREE` on H2 console (url mentioned above)
    - For testing, Postman can be used, like `http://localhost:3000/api/student/1`


- **Project Configuration Description**
    - Runs on port `3000`. Can be configured in application.properties file.
    - Port can be configured in `application.properties`



- **Project Working Description**
    - The degrees/majors already included into the database, from `data.sql`. New degrees/majors can also be created via the
      defined REST API
    - As of now, operations related to Student are only implemented, the project will evolve with time



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
            - `PUT /api/student/{id}`

