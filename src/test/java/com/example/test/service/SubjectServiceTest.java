package com.example.test.service;

import com.example.test.entity.Subject;
import com.example.test.exception.SubjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubjectServiceTest {

    @Autowired
    private SubjectService subjectService;


    @Test
    void save() throws SubjectNotFoundException {
        subjectService.save(getSubject());

        Subject byId = subjectService.findById(getSubject().getId());

        assertEquals(1L, byId.getId());
        assertEquals("Java", byId.getName());
        assertEquals(101, byId.getRoomNumber());
    }

    @Test
    void findById() throws SubjectNotFoundException {
        subjectService.save(getSubject());

        Subject byId = subjectService.findById(getSubject().getId());

        assertEquals(1L, byId.getId());
        assertEquals("Java", byId.getName());
        assertEquals(101, byId.getRoomNumber());

    }

    @Test
    void update() throws SubjectNotFoundException {
        subjectService.save(getSubject());

        Subject byId = subjectService.findById(getSubject().getId());
        Subject update = subjectService.update(getSubject2(), byId.getId());

        assertEquals(1L, update.getId());
        assertEquals("JavaCore", update.getName());
        assertEquals(220, update.getRoomNumber());
    }

    private Subject getSubject(){
        return Subject.builder()
                .id(1L)
                .name("Java")
                .roomNumber(101)
                .build();
    }

    private Subject getSubject2(){
        return Subject.builder()
                .name("JavaCore")
                .roomNumber(220)
                .build();
    }
}