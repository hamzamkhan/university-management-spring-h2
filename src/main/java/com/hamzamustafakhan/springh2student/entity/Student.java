package com.hamzamustafakhan.springh2student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column(name = "batch_year")
    private int batchYear;
    @Column(name = "fathers_name")
    private String fathersName;
    @Column
    private Date dob;
    @OneToOne
    @JoinColumn(name = "major")
    private Major major;
    @OneToOne
    @JoinColumn(name = "degree")
    private Degree degree;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
