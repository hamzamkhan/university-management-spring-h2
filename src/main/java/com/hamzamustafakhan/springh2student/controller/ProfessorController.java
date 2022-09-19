package com.hamzamustafakhan.springh2student.controller;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.domain.GenericResponse;
import com.hamzamustafakhan.springh2student.dto.ProfessorDTO;
import com.hamzamustafakhan.springh2student.dto.StudentDTO;
import com.hamzamustafakhan.springh2student.entity.Professor;
import com.hamzamustafakhan.springh2student.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/professor")
@RestController
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<GenericResponse> createProfessor(@Valid @RequestBody ProfessorDTO professorDTO){
        GenericResponse<String> genericResponse = new GenericResponse<>();
        String response = professorService.createProfessor(professorDTO);
        genericResponse.setResponse(response);
        genericResponse.setStatus(Constants.SUCCESS);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> professorById(@PathVariable("id") int id){
        ProfessorDTO professor = professorService.findProfessorInfoById(id);
        GenericResponse<ProfessorDTO> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(professor);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listStudents(){
        List<Professor> professors = professorService.getProfessors();
        GenericResponse<List> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(professors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteStudentById(@PathVariable("id") int id){
        String deleteResponse = professorService.deleteProfessor(id);
        GenericResponse<String> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(deleteResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updateStudent(@PathVariable("id") int id, @RequestBody String email){
        String updateResponse = professorService.updateProfessorEmail(id, email);
        GenericResponse<String> response = new GenericResponse<>();
        response.setStatus(Constants.SUCCESS);
        response.setResponse(updateResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
