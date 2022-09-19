package com.hamzamustafakhan.springh2student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "student", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course", nullable = false)
    private Course course;
    @ManyToOne
    @JoinColumn(name = "professor", nullable = false)
    private Professor professor;
}
