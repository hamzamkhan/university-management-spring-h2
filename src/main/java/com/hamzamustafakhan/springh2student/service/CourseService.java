package com.hamzamustafakhan.springh2student.service;


import com.hamzamustafakhan.springh2student.entity.Course;

import java.util.List;

public interface CourseService {
    Course findCourseById(int id);

    List<Course> findAll();
}
