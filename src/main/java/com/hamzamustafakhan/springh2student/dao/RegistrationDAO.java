package com.hamzamustafakhan.springh2student.dao;

import com.hamzamustafakhan.springh2student.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDAO extends JpaRepository<Registration, Integer> {
}
