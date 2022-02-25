package com.example.test.service;

import com.example.test.dto.schedule.FindScheduleRequest;
import com.example.test.entity.Schedule;
import com.example.test.entity.Student;
import com.example.test.entity.Subject;
import com.example.test.exception.DayOfWeekNotFoundException;
import com.example.test.exception.ScheduleNotFoundException;
import com.example.test.exception.StudentNotExistException;
import com.example.test.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;

    @Test
    void create() throws ScheduleNotFoundException {
        scheduleService.create(getSchedule());

        Schedule byId = scheduleService.findById(getSchedule().getId());

        assertEquals(1L, byId.getId());
        assertEquals(DayOfWeek.MONDAY, byId.getDayOfWeek());
        assertEquals("25J", byId.getNumberGroup());
    }

    @Test
    void findById() throws ScheduleNotFoundException {
        scheduleService.create(getSchedule());

        Schedule byId = scheduleService.findById(getSchedule().getId());

        assertEquals(1L, byId.getId());
        assertEquals(DayOfWeek.MONDAY, byId.getDayOfWeek());
        assertEquals("25J", byId.getNumberGroup());
    }

    @Test
    void findByDay() throws DayOfWeekNotFoundException {
        scheduleService.create(getSchedule());

        Schedule byId = scheduleService.findByDay(DayOfWeek.MONDAY);

        assertEquals(1L, byId.getId());
        assertEquals(DayOfWeek.MONDAY, byId.getDayOfWeek());
        assertEquals("25J", byId.getNumberGroup());
    }


    @Test
    void update() throws ScheduleNotFoundException {
        scheduleService.create(getSchedule());

        Schedule byId = scheduleService.findById(getSchedule().getId());
        Schedule update = scheduleService.update(getSchedule2(), byId.getId());

        assertEquals(1L, update.getId());
        assertEquals(DayOfWeek.TUESDAY, update.getDayOfWeek());
        assertEquals("25P", update.getNumberGroup());
        assertEquals(Collections.singletonList(new Subject(1L, "Java", 101)), update.getSubject());

    }

    @Test
    void findScheduleForStudent() throws StudentNotExistException {
        scheduleService.create(getSchedule());
        studentService.save(getStudent());
        subjectService.save(getSubject());

        Student byStudent = studentService.findByNameAndLastName("Ivan", "Golub");
        List<Schedule> schedules = scheduleRepository.findByNumberGroupAndDayOfWeek(byStudent.getNumberGroup(), DayOfWeek.MONDAY);



        assertEquals(1L, schedules.get(0).getId());
        assertEquals(DayOfWeek.MONDAY, schedules.get(0).getDayOfWeek());
        assertEquals("25J", schedules.get(0).getNumberGroup());
        assertEquals(Collections.singletonList(new Subject(1L, "Java", 101)), schedules.get(0).getSubject());

    }



    private Schedule getSchedule(){
        return Schedule.builder()
                .id(1L)
                .dayOfWeek(DayOfWeek.MONDAY)
                .numberGroup("25J")
                .subject(Collections.singletonList(new Subject(1L, "Java", 101)))
                .build();
    }

    private Schedule getSchedule2(){
        return Schedule.builder()
                .dayOfWeek(DayOfWeek.TUESDAY)
                .numberGroup("25P")
                .subject(Collections.singletonList(new Subject(1L, "Java", 101)))
                .build();
    }

    private Student getStudent(){
        return Student.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Golub")
                .middleName("Vladimirovich")
                .course(1)
                .numberGroup("25J")
                .build();
    }

    private Subject getSubject(){
        return Subject.builder()
                .id(1L)
                .name("Java")
                .roomNumber(101)
                .build();
    }
}