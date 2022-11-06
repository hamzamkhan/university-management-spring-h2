package com.hamzamustafakhan.springh2student.controller;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.domain.GenericResponse;
import com.hamzamustafakhan.springh2student.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/degree")
public class DegreeController {

    @Autowired
    private DegreeService degreeService;

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listDegrees(){
        GenericResponse<List> genericResponse = new GenericResponse<>();
        genericResponse.setResponse(degreeService.findAll());
        genericResponse.setStatus(Constants.SUCCESS);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
