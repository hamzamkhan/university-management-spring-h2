package com.hamzamustafakhan.springh2student.service.impl;


import com.hamzamustafakhan.springh2student.dao.MajorDAO;
import com.hamzamustafakhan.springh2student.entity.Major;
import com.hamzamustafakhan.springh2student.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorDAO majorDAO;

    @Override
    public Major findCourseById(int id) {
        Optional<Major> optionalMajor = majorDAO.findById(id);
        Major major = optionalMajor.get();
        return major;
    }

    @Override
    public List<Major> findAll() {
        return majorDAO.findAll();
    }
}
