package com.hamzamustafakhan.springh2student.dao;

import com.hamzamustafakhan.springh2student.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
}
