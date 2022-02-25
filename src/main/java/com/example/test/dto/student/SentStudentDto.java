package com.example.test.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SentStudentDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private Integer course;

    private String numberGroup;

}
