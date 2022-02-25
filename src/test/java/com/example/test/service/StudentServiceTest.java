package com.example.test.service;

import com.example.test.entity.Student;
import com.example.test.exception.StudentNotExistException;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.repository.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;


    @Test
    void findById() throws StudentNotFoundException {
        studentService.save(getStudent());
        Student byId = studentService.findById(getStudent().getId());

        assertNotNull(byId);
        assertEquals("Ivan", byId.getFirstName());
        assertEquals("Golub", byId.getLastName());
        assertEquals("Vladimirovich", byId.getMiddleName());
        assertEquals(1, byId.getCourse());
        assertEquals("25T", byId.getNumberGroup());

    }

    @Test
    void save() throws StudentNotExistException {
        studentService.save(getStudent());
        Student byId = studentService.findByNameAndLastName("Ivan", "Golub");

        assertNotNull(byId);
        assertEquals("Ivan", byId.getFirstName());
        assertEquals("Golub", byId.getLastName());
    }



    @Test
    void findByNameAndLastName() throws StudentNotExistException {
        studentService.save(getStudent());

        Student byId = studentService.findByNameAndLastName("Ivan", "Golub");
        assertNotNull(byId);
        assertEquals("Ivan", byId.getFirstName());
        assertEquals("Golub", byId.getLastName());
    }

    @Test
    void update() throws StudentNotFoundException {
        studentService.save(getStudent());

        Student byId = studentService.findById(getStudent().getId());
        Student updateStudent = studentService.update(getStudent2(), byId.getId());

        assertNotNull(updateStudent);
        assertEquals("Pavel", updateStudent.getFirstName());
        assertEquals("Novik", updateStudent.getLastName());
        assertEquals("Maximovich", updateStudent.getMiddleName());
        assertEquals(2, updateStudent.getCourse());
        assertEquals("28J", updateStudent.getNumberGroup());

    }


    private Student getStudent() {
        return Student.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Golub")
                .middleName("Vladimirovich")
                .course(1)
                .numberGroup("25T")
                .build();
    }


    private Student getStudent2() {
        return Student.builder()
                .firstName("Pavel")
                .lastName("Novik")
                .middleName("Maximovich")
                .course(2)
                .numberGroup("28J")
                .build();
    }


}