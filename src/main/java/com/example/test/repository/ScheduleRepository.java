package com.example.test.repository;

import com.example.test.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    Optional<Schedule> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<Schedule> findByNumberGroupAndDayOfWeek(String numberGroup, DayOfWeek dayOfWeek);
}
