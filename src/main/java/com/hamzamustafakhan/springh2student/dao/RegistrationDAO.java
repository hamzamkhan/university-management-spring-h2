package com.hamzamustafakhan.springh2student.dao;

import com.hamzamustafakhan.springh2student.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDAO extends JpaRepository<Registration, Integer> {

    @Query("SELECT r FROM Registration r where r.student.id =:studentId AND r.course.id =:courseId")
    Registration findRegistrationByCombination(int studentId, int courseId);

}
