package com.hamzamustafakhan.springh2student.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Date of birth is required")
    private String dob;

    @JsonProperty("fathers_name")
    @NotBlank(message = "Father's name is required")
    private String fathersname;

    @Email(message = "Email should follow the proper pattern")
    @NotBlank(message = "Email is required")
    private String email;

    @JsonProperty("batch_year")
    @Min(value = 1990, message = "Cannot be before 1990")
    @Max(value = 2022, message = "Cannot be more than 2022")
    private int batchYear;

    @NotBlank(message = "Major is required")
    private String major;

    @NotBlank(message = "Degree is required")
    private String degree;
}
