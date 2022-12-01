package com.hamzamustafakhan.springh2student.service.impl;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.dao.ProfessorDAO;
import com.hamzamustafakhan.springh2student.dto.ProfessorDTO;
import com.hamzamustafakhan.springh2student.entity.Professor;
import com.hamzamustafakhan.springh2student.service.ProfessorService;
import com.hamzamustafakhan.springh2student.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorDAO professorDAO;

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat dateFormatUI = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String createProfessor(ProfessorDTO professorDTO) throws ParseException {
        if(professorDAO.findByEmail(professorDTO.getEmail()) != null){
            return "PROFESSOR EXISTS";
        }
        Date dob = dateFormat.parse(professorDTO.getDob());

        Professor professor = new Professor();
        professor.setEmail(professorDTO.getEmail());
        professor.setName(professorDTO.getName());
        professor.setMobile(professorDTO.getMobile());
        professor.setCreatedAt(new Date());
        professor.setDob(dob);
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
        ProfessorDTO professorDTO = new ProfessorDTO(String.valueOf(id), professor.getName(), professor.getEmail(), professor.getMobile(), dateFormatUI.format(professor.getDob()));
        return professorDTO;
    }

    @Override
    public List<ProfessorDTO> getProfessors() {
        List<Professor> professors = professorDAO.findAll();
        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        for(Professor professor : professors){
            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO.setId(String.valueOf(professor.getId()));
            professorDTO.setName(professor.getName());
            professorDTO.setEmail(professor.getEmail());
            professorDTO.setMobile(professor.getMobile());
            professorDTO.setDob(dateFormatUI.format(professor.getDob()));

            professorDTOList.add(professorDTO);
        }
        return professorDTOList;
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

    @Override
    public ProfessorDTO updateProfessor(int id, ProfessorDTO professorDTO) {
        try {
            Optional<Professor> optionalProfessor = professorDAO.findById(id);
            Professor professor = optionalProfessor.get();
            Date dob = dateFormat.parse(professorDTO.getDob());

            professor.setMobile(professorDTO.getMobile());
            professor.setEmail(professorDTO.getEmail());
            professor.setDob(dob);

            professorDAO.saveAndFlush(professor);
        } catch (Exception e){
            e.printStackTrace();
        }
        return professorDTO;
    }
}
