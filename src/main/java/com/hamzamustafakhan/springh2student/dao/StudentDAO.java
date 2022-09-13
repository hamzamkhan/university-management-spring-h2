package com.hamzamustafakhan.springh2student.dao;

import com.hamzamustafakhan.springh2student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {

    Student findStudentByEmail(String email);
}
