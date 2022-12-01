package com.hamzamustafakhan.springh2student.service;


import com.hamzamustafakhan.springh2student.dto.ProfessorDTO;
import com.hamzamustafakhan.springh2student.entity.Professor;

import java.text.ParseException;
import java.util.List;

public interface ProfessorService {
    String createProfessor(ProfessorDTO professorDTO) throws ParseException;

    ProfessorDTO findProfessorInfoById(int id);

    List<ProfessorDTO> getProfessors();

    String deleteProfessor(int id);

    String updateProfessorEmail(int id, String email);

    Professor findProfessorById(int id);

    ProfessorDTO updateProfessor(int id, ProfessorDTO professorDTO);
}

