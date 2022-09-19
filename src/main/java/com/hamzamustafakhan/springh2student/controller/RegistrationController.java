package com.hamzamustafakhan.springh2student.controller;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.domain.GenericResponse;
import com.hamzamustafakhan.springh2student.dto.RegistrationDTO;
import com.hamzamustafakhan.springh2student.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<GenericResponse> createRegistration(@Valid @RequestBody RegistrationDTO registrationDTO){
        GenericResponse<String> genericResponse = new GenericResponse<>();
        String response = registrationService.createRegistration(registrationDTO);
        genericResponse.setResponse(response);
        genericResponse.setStatus(Constants.SUCCESS);
        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> listRegistrations(){
        GenericResponse<List> genericResponse = new GenericResponse<>();
        List<RegistrationDTO> list = registrationService.getRegistrations();
        genericResponse.setResponse(list);
        genericResponse.setStatus(Constants.SUCCESS);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
