package com.hamzamustafakhan.springh2student.dao;

import com.hamzamustafakhan.springh2student.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Integer> {
    Professor findByEmail(String email);
}
