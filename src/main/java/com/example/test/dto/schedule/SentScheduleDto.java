package com.example.test.dto.schedule;


import com.example.test.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SentScheduleDto {

    private Long id;

    private List<Subject> subject;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private String numberGroup;
}
