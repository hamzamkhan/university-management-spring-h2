package com.hamzamustafakhan.springh2student.unit;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class UniversityManagementApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }


    @Test
    @SneakyThrows
    public void createStudent(){
        log.info("Testing: creating a student");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                .content("{\n" +
                        "    \"name\":\"Hamza Khan\",\n" +
                        "    \"dob\":\"26/10/1996\",\n" +
                        "    \"email\":\"hamza@gmail.com\",\n" +
                        "    \"fathers_name\":\"Muzaffar Khan\",\n" +
                        "    \"batch_year\":2015,\n" +
                        "    \"major\":1,\n" +
                        "    \"degree\":1\n" +
                        "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(ResultMatcher.matchAll(status().isOk()))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void createExistingStudent(){
        log.info("Testing: creating an existing student");
        createStudent();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                        .content("{\n" +
                                "    \"name\":\"Hamza Khan\",\n" +
                                "    \"dob\":\"26/10/1996\",\n" +
                                "    \"email\":\"hamza@gmail.com\",\n" +
                                "    \"fathers_name\":\"Muzaffar Khan\",\n" +
                                "    \"batch_year\":2015,\n" +
                                "    \"major\":1,\n" +
                                "    \"degree\":1\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(ResultMatcher.matchAll(status().isOk()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("STUDENT EXISTS"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void getStudentById(){
        log.info("Testing: get student by id");
        createStudent();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['response'].name").value("Hamza Khan"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void getStudentByIdNotFound(){
        log.info("Testing: get student by id");
        createStudent();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/2"))
                .andExpect(status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Server Error"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void updateStudent(){
        log.info("Testing: update student");
        createStudent();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/student/email/1")
                .content("{\n" +
                        "    \"hmkscorpio@gmail.com\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("Updated"))
                .andDo(print());
    }


    @Test
    @SneakyThrows
    public void createProfessor(){
        log.info("Testing: create professor");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/professor")
                .content("{\n" +
                        " \"name\":\"John Doe\",\n" +
                        "\"email\":\"johndoe@gmail.com\",\n" +
                        "\"mobile\":\"03333232939\",\n" +
                        "\"dob\":\"26/10/1996\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void createProfessorFailed(){
        log.info("Testing: create existing professor");
        createProfessor();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/professor")
                        .content("{\n" +
                                "    \"name\":\"John Doe\",\n" +
                                "    \"email\":\"johndoe@gmail.com\",\n" +
                                "    \"mobile\":\"03333232939\",\n" +
                                "    \"dob\":\"26/10/1996\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response").value("PROFESSOR EXISTS"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void getProfessorById(){
        log.info("Testing: get professor by id");
        createProfessor();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/professor/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$['response'].name").value("John Doe"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void getProfessorByIdNotFound(){
        log.info("Testing: get professor by id");
        createStudent();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/professor/2"))
                .andExpect(status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Server Error"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    public void createRegistration(){
        log.info("Testing: creating registration");
        createStudent();
        createProfessor();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/registration")
                .content("{\n" +
                        "    \"student\":\"1\",\n" +
                        "    \"course\":\"1\",\n" +
                        "    \"professor\":\"1\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }






}
