package com.hamzamustafakhan.springh2student.service.impl;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.dao.RegistrationDAO;
import com.hamzamustafakhan.springh2student.dto.ProfessorDTO;
import com.hamzamustafakhan.springh2student.dto.RegistrationDTO;
import com.hamzamustafakhan.springh2student.dto.StudentDTO;
import com.hamzamustafakhan.springh2student.entity.Course;
import com.hamzamustafakhan.springh2student.entity.Professor;
import com.hamzamustafakhan.springh2student.entity.Registration;
import com.hamzamustafakhan.springh2student.entity.Student;
import com.hamzamustafakhan.springh2student.service.CourseService;
import com.hamzamustafakhan.springh2student.service.ProfessorService;
import com.hamzamustafakhan.springh2student.service.RegistrationService;
import com.hamzamustafakhan.springh2student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationDAO registrationDAO;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Override
    public String createRegistration(RegistrationDTO registrationDTO) {
        int studentId = Integer.parseInt(registrationDTO.getStudent());
        int courseId = Integer.parseInt(registrationDTO.getCourse());
        int professorId = Integer.parseInt(registrationDTO.getProfessor());
        Registration existingRegistration = registrationDAO.findRegistrationByCombination(studentId, courseId);
        if(existingRegistration != null){
            return "REGISTRATION EXISTS";
        }
        Student student = studentService.findStudentById(studentId);
        if(student == null){
            return "STUDENT NOT FOUND";
        }
        Professor professor = professorService.findProfessorById(professorId);
        if(professor == null){
            return "PROFESSOR NOT FOUND";
        }
        Course course = courseService.findCourseById(courseId);
        if(course == null){
            return "COURSE NOT FOUND";
        }

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setProfessor(professor);
        registrationDAO.save(registration);

        return Constants.CREATED;
    }

    @Override
    public List<RegistrationDTO> getRegistrations() {
        List<Registration> registrations = registrationDAO.findAll();
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        for(Registration registration : registrations){
            RegistrationDTO registrationDTO = new RegistrationDTO();
            registrationDTO.setCourse(registration.getCourse().getName());
            registrationDTO.setProfessor(registration.getProfessor().getName());
            registrationDTO.setStudent(registration.getStudent().getName());

            registrationDTOS.add(registrationDTO);
        }
        return registrationDTOS;
    }
}
