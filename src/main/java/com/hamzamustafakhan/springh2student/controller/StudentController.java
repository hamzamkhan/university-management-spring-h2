package com.hamzamustafakhan.springh2student.controller;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.domain.GenericResponse;
import com.hamzamustafakhan.springh2student.dto.StudentDTO;
import com.hamzamustafakhan.springh2student.entity.Student;
import com.hamzamustafakhan.springh2student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<GenericResponse> saveStudent(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        String responseStudent = studentService.createStudent(studentDTO);
        GenericResponse<String> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(responseStudent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> studentById(@PathVariable("id") int id){
        StudentDTO studentDTO = studentService.findStudentInfoById(id);
        GenericResponse<StudentDTO> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(studentDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listStudents(){
        List<StudentDTO> studentDTOs = studentService.getStudents();
        GenericResponse<List> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(studentDTOs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteStudentById(@PathVariable("id") int id){
        String deleteResponse = studentService.deleteStudent(id);
        GenericResponse<String> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(deleteResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<GenericResponse> updateStudentEmail(@PathVariable("id") int id, @RequestBody String email){
        String updateResponse = studentService.updateStudentEmail(id, email);
        GenericResponse<String> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(updateResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updateStudent(@PathVariable("id") int id, @RequestBody StudentDTO studentDTO) throws Exception {
        GenericResponse<StudentDTO> genericResponse = new GenericResponse<>();
        StudentDTO responseDTO = studentService.updateStudent(id, studentDTO);
        genericResponse.setResponse(responseDTO);
        genericResponse.setStatus(Constants.SUCCESS);

        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
