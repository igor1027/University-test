package com.example.test.dto.schedule;

import com.example.test.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.DayOfWeek;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RequestScheduleDto {

    @NotBlank
    private List<Subject> subject;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @NotBlank
    private String numberGroup;
}
