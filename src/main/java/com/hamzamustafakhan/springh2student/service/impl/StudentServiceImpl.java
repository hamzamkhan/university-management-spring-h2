package com.hamzamustafakhan.springh2student.service.impl;

import com.hamzamustafakhan.springh2student.constants.Constants;
import com.hamzamustafakhan.springh2student.dao.DegreeDAO;
import com.hamzamustafakhan.springh2student.dao.MajorDAO;
import com.hamzamustafakhan.springh2student.dao.StudentDAO;
import com.hamzamustafakhan.springh2student.dto.StudentDTO;
import com.hamzamustafakhan.springh2student.entity.Degree;
import com.hamzamustafakhan.springh2student.entity.Major;
import com.hamzamustafakhan.springh2student.entity.Student;
import com.hamzamustafakhan.springh2student.service.StudentService;
import com.hamzamustafakhan.springh2student.util.Utility;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private DegreeDAO degreeDAO;

    @Autowired
    private MajorDAO majorDAO;

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat dateFormatUI = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String createStudent(StudentDTO studentDTO) throws Exception {
        if(studentDAO.findStudentByEmail(studentDTO.getEmail()) != null){
            return "STUDENT EXISTS";
        }
        Optional<Degree> optionalDegree = degreeDAO.findById(Integer.parseInt(studentDTO.getDegree()));
        Optional<Major> optionalMajor = majorDAO.findById(Integer.parseInt(studentDTO.getMajor()));
        Student student = new Student();
        Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(studentDTO.getDob());

        student.setDob(dob);
        student.setName(studentDTO.getName());
        student.setFathersName(studentDTO.getFathersname());
        student.setEmail(studentDTO.getEmail());
        student.setBatchYear(studentDTO.getBatchYear());
        student.setMajor(optionalMajor.get());
        student.setDegree(optionalDegree.get());
        student.setCreatedAt(new Date());

        studentDAO.save(student);
        return Constants.CREATED;
    }

    @Override
    public StudentDTO findStudentInfoById(int id) {
        StudentDTO studentDTO = new StudentDTO();
        Optional<Student> optionalStudent = studentDAO.findById(id);
        Student student = optionalStudent.get();


        studentDTO.setId(String.valueOf(student.getId()));
        studentDTO.setMajor(student.getMajor().getName());
        studentDTO.setName(student.getName());
        studentDTO.setDegree(student.getDegree().getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setDob(dateFormatUI.format(student.getDob()));
        studentDTO.setFathersname(student.getFathersName());
        studentDTO.setBatchYear(student.getBatchYear());
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getStudents() {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        List<Student> students = studentDAO.findAll();
        for(Student student : students){
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setId(String.valueOf(student.getId()));
            studentDTO.setMajor(student.getMajor().getName());
            studentDTO.setName(student.getName());
            studentDTO.setDegree(student.getDegree().getName());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setDob(dateFormat.format(student.getDob()));
            studentDTO.setFathersname(student.getFathersName());
            studentDTO.setBatchYear(student.getBatchYear());

            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }

    @Override
    public String deleteStudent(int id) {
        studentDAO.deleteById(id);
        return Constants.SUCCESS;
    }

    @Override
    public String updateStudentEmail(int id, String email) {
        email = Utility.parseEmail(email);
        Optional<Student> optionalStudent = studentDAO.findById(id);
        Student student = optionalStudent.get();

        student.setEmail(email);
        student.setUpdatedAt(new Date());
        studentDAO.save(student);

        return Constants.UPDATED;
    }

    @Override
    public Student findStudentById(int id) {
        Optional<Student> optionalStudent = studentDAO.findById(id);
        Student student = optionalStudent.get();

        return student;
    }

    @Override
    public StudentDTO updateStudent(int id, StudentDTO studentDTO) throws Exception {
        try {
            Optional<Student> optionalStudent = studentDAO.findById(id);
            Student student = optionalStudent.get();

            Optional<Degree> optionalDegree = degreeDAO.findById(Integer.parseInt(studentDTO.getDegree()));
            Optional<Major> optionalMajor = majorDAO.findById(Integer.parseInt(studentDTO.getMajor()));
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(studentDTO.getDob());

             student.setDob(dob);
            student.setName(studentDTO.getName());
            student.setFathersName(studentDTO.getFathersname());
            student.setEmail(studentDTO.getEmail());
            student.setBatchYear(studentDTO.getBatchYear());
            student.setMajor(optionalMajor.get());
            student.setDegree(optionalDegree.get());
            student.setUpdatedAt(new Date());

            studentDAO.saveAndFlush(student);
        } catch (Exception e){
            e.printStackTrace();
        }


        return studentDTO;
    }
}
