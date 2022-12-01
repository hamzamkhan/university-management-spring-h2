package com.hamzamustafakhan.springh2student.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotBlank(message = "Name is required")
    private String name;
    @Column
    @Email(message = "Email should follow the proper pattern")
    @NotBlank(message = "Email is required")
    private String email;
    @Column
    @NotBlank(message = "Mobile number is required")
    private String mobile;
    @Column(name = "created_at")
    @Nullable
    private Date createdAt;
    @Column(name = "updated_at")
    @Nullable
    private Date updatedAt;
    @Column
    private Date dob;
}
