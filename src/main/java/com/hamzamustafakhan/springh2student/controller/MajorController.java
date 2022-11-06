package com.hamzamustafakhan.springh2student.controller;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.domain.GenericResponse;
import com.hamzamustafakhan.springh2student.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listMajors(){
        GenericResponse<List> genericResponse = new GenericResponse<>();
        genericResponse.setResponse(majorService.findAll());
        genericResponse.setStatus(Constants.SUCCESS);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
