package com.hamzamustafakhan.springh2student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email should follow the proper pattern")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Mobile number is required")
    @Length(min = 11, max = 11, message = "Mobile should be of 11 digits")
    private String mobile;
}
