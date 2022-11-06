package com.hamzamustafakhan.springh2student.controller;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.domain.GenericResponse;
import com.hamzamustafakhan.springh2student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listMajors(){
        GenericResponse<List> genericResponse = new GenericResponse<>();
        genericResponse.setResponse(courseService.findAll());
        genericResponse.setStatus(Constants.SUCCESS);
        return ResponseEntity.ok(genericResponse);
    }
}
