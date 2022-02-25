package com.example.test.controller;


import com.example.test.dto.schedule.FindScheduleRequest;
import com.example.test.dto.schedule.RequestScheduleDto;
import com.example.test.dto.schedule.SentScheduleDto;
import com.example.test.dto.student.RequestStudentDto;
import com.example.test.dto.student.SentStudentDto;
import com.example.test.entity.Schedule;
import com.example.test.entity.Student;
import com.example.test.exception.DayOfWeekNotFoundException;
import com.example.test.exception.ScheduleNotFoundException;
import com.example.test.exception.StudentNotExistException;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ModelMapper mapper;


    @PostMapping("/create")
    @Operation(summary = "Create new schedule")
    public ResponseEntity<?> createSchedule(@Valid @RequestBody RequestScheduleDto scheduleDto){
        Schedule created = scheduleService.create(mapper.map(scheduleDto, Schedule.class));
        return new ResponseEntity<>(mapper.map(created, SentScheduleDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/day/{day}")
    @Operation(summary = "Show schedule for the day")
    public ResponseEntity<Schedule> findByDay(@PathVariable("day") DayOfWeek day) throws DayOfWeekNotFoundException{
        Schedule getByDay = scheduleService.findByDay(day);
        return new ResponseEntity<>(getByDay, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updating an existing schedule")
    public ResponseEntity<?> updateschedule(@PathVariable long id, @Valid @RequestBody RequestScheduleDto scheduleDto) throws ScheduleNotFoundException {
        Schedule updateSchedule = scheduleService.update(mapper.map(scheduleDto, Schedule.class), id);
        return new ResponseEntity<>(mapper.map(updateSchedule, SentScheduleDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting schedule")
    public ResponseEntity<?> deleteSchedule(@PathVariable long id) throws ScheduleNotFoundException {
        Schedule deleted = scheduleService.deleteById(id);
        return new ResponseEntity<>(mapper.map(deleted,SentScheduleDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/day/{day}")
    @Operation(summary = "Deleting schedule for the day")
    public ResponseEntity<?> deleteByDay(@PathVariable("day") DayOfWeek day) throws DayOfWeekNotFoundException {
        Schedule deleted = scheduleService.deleteByDay(day);
        return new ResponseEntity<>(mapper.map(deleted,SentScheduleDto.class), HttpStatus.OK);
    }

    @GetMapping(value = "/student")
    @Operation(summary = "Show schedule for student")
    public ResponseEntity<List<Schedule>> findScheduleForStudent(@RequestBody FindScheduleRequest scheduleRequest) throws StudentNotExistException {
        List<Schedule> scheduleForStudent = scheduleService.findScheduleForStudent(scheduleRequest);
        return new ResponseEntity<>(scheduleForStudent, HttpStatus.OK);
    }

}
