package com.example.test.service;

import com.example.test.entity.Subject;
import com.example.test.entity.Teacher;
import com.example.test.exception.TeacherNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;


    @Test
    void save() throws TeacherNotFoundException {
        teacherService.save(getTeacher());

        Teacher byId = teacherService.findById(getTeacher().getId());

        assertEquals(1L, byId.getId());
        assertEquals("Masha", byId.getFirstName());
        assertEquals("Ladutko", byId.getLastName());
    }


    @Test
    void findById() throws TeacherNotFoundException {
        teacherService.save(getTeacher());

        Teacher byId = teacherService.findById(getTeacher().getId());

        assertEquals(1L, byId.getId());
        assertEquals("Masha", byId.getFirstName());
        assertEquals("Ladutko", byId.getLastName());
        assertEquals("Vitalievna", byId.getMiddleName());

    }

    @Test
    void update() throws TeacherNotFoundException {
        teacherService.save(getTeacher());

        Teacher byId = teacherService.findById(getTeacher().getId());
        Teacher update = teacherService.update(getTeacher2(), byId.getId());

        assertEquals(1L, byId.getId());
        assertEquals("Pavel", update.getFirstName());
        assertEquals("Kishko", update.getLastName());
        assertEquals("Maximovich", update.getMiddleName());
    }


    private Teacher getTeacher(){
        return Teacher.builder()
                .id(1L)
                .firstName("Masha")
                .lastName("Ladutko")
                .middleName("Vitalievna")
                .subject(new Subject(1L,"Java", 101))
                .build();
    }

    private Teacher getTeacher2(){
        return Teacher.builder()
                .firstName("Pavel")
                .lastName("Kishko")
                .middleName("Maximovich")
                .subject(new Subject(1L,"Java", 101))
                .build();
    }
}