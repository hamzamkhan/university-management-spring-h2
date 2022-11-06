package com.hamzamustafakhan.springh2student.service;

import com.hamzamustafakhan.springh2student.entity.Degree;
import java.util.List;

public interface DegreeService {
    Degree findCourseById(int id);

    List<Degree> findAll();
}
