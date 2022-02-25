package com.example.test.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FindScheduleRequest {

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

}
