package com.hamzamustafakhan.springh2student.service;


import com.hamzamustafakhan.springh2student.entity.Professor;

import java.util.List;

public interface ProfessorService {
    String createProfessor(Professor professor);

    Professor findProfessorById(int id);

    List<Professor> getProfessors();

    String deleteProfessor(int id);

    String updateProfessorEmail(int id, String email);
}

