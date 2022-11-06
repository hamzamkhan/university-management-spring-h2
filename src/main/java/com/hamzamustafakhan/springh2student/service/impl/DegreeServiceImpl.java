package com.hamzamustafakhan.springh2student.service.impl;


import com.hamzamustafakhan.springh2student.dao.DegreeDAO;
import com.hamzamustafakhan.springh2student.entity.Degree;
import com.hamzamustafakhan.springh2student.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeServiceImpl implements DegreeService {

    @Autowired
    private DegreeDAO degreeDAO;

    @Override
    public Degree findCourseById(int id) {
        Optional<Degree> optionalDegree = degreeDAO.findById(id);
        Degree degree = optionalDegree.get();
        return degree;
    }

    @Override
    public List<Degree> findAll() {
        return degreeDAO.findAll();
    }
}
