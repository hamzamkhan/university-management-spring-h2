package com.hamzamustafakhan.springh2student.service.impl;

import com.hamzamustafakhan.springh2student.dao.CourseDAO;
import com.hamzamustafakhan.springh2student.entity.Course;
import com.hamzamustafakhan.springh2student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @Override
    public Course findCourseById(int id) {
        Optional<Course> optionalCourse = courseDAO.findById(id);
        Course course = optionalCourse.get();
        return course;
    }
}
