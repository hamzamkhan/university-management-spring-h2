package com.hamzamustafakhan.springh2student.service.impl;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.dao.ProfessorDAO;
import com.hamzamustafakhan.springh2student.dto.ProfessorDTO;
import com.hamzamustafakhan.springh2student.entity.Professor;
import com.hamzamustafakhan.springh2student.service.ProfessorService;
import com.hamzamustafakhan.springh2student.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorDAO professorDAO;

    @Override
    public String createProfessor(ProfessorDTO professorDTO) {
        if(professorDAO.findByEmail(professorDTO.getEmail()) != null){
            return "PROFESSOR EXISTS";
        }
        Professor professor = new Professor();
        professor.setEmail(professorDTO.getEmail());
        professor.setName(professorDTO.getName());
        professor.setMobile(professorDTO.getMobile());
        professor.setCreatedAt(new Date());
        professorDAO.save(professor);

        return Constants.SUCCESS;
    }

    @Override
    public Professor findProfessorById(int id) {
        Optional<Professor> optionalProfessor = professorDAO.findById(id);
        Professor professor = optionalProfessor.get();
        return professor;
    }

    @Override
    public ProfessorDTO findProfessorInfoById(int id) {
        Optional<Professor> optionalProfessor = professorDAO.findById(id);
        Professor professor = optionalProfessor.get();
        ProfessorDTO professorDTO = new ProfessorDTO(professor.getName(), professor.getEmail(), professor.getMobile());
        return professorDTO;
    }

    @Override
    public List<Professor> getProfessors() {
        List<Professor> professors = professorDAO.findAll();
        return professors;
    }

    @Override
    public String deleteProfessor(int id) {
        professorDAO.deleteById(id);
        return Constants.SUCCESS;
    }

    @Override
    public String updateProfessorEmail(int id, String email) {
        email = Utility.parseEmail(email);
        Optional<Professor> optionalProfessor = professorDAO.findById(id);
        Professor professor = optionalProfessor.get();
        professor.setEmail(email);
        professor.setUpdatedAt(new Date());
        professorDAO.save(professor);
        return Constants.UPDATED;
    }
}
