package com.nhnacademy.edu.springboot.student.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentModifyRequest {
    @NotBlank
    String name;

    @Email
    String email;

    @Min(0) @Max(100)
    int score;

    @NotBlank @Size(max = 200)
    String comment;
}
