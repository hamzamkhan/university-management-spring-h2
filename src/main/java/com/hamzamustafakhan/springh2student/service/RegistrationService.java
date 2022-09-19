package com.hamzamustafakhan.springh2student.service;

import com.hamzamustafakhan.springh2student.dto.RegistrationDTO;

import java.util.List;

public interface RegistrationService {

    String createRegistration(RegistrationDTO registrationDTO);

    List<RegistrationDTO> getRegistrations();
}
