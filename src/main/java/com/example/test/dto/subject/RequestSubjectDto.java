package com.example.test.dto.subject;

import com.example.test.dto.teacher.SentTeacherDto;
import com.example.test.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RequestSubjectDto {


    private Long id;

    private String name;

    private int roomNumber;

//    private Teacher teacher;
}
