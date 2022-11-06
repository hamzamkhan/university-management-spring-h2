package com.hamzamustafakhan.springh2student.service;

import com.hamzamustafakhan.springh2student.entity.Major;
import java.util.List;

public interface MajorService {
    Major findCourseById(int id);

    List<Major> findAll();
}
