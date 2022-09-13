package com.hamzamustafakhan.springh2student.service;



import com.hamzamustafakhan.springh2student.dto.StudentDTO;
import com.hamzamustafakhan.springh2student.entity.Student;

import java.util.List;

public interface StudentService {
    String createStudent(StudentDTO studentDTO) throws Exception;
    StudentDTO findStudentById(int id);
    List<StudentDTO> getStudents();
    String deleteStudent(int id);

    String updateStudentEmail(int id, String email);
}
