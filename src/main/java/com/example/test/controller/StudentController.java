package com.example.test.controller;

import com.example.test.dto.student.RequestStudentDto;
import com.example.test.dto.student.SentStudentDto;
import com.example.test.entity.Student;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create")
    @Operation(summary = "Create new Student")
    public ResponseEntity<?> createStudent(@Valid @RequestBody RequestStudentDto studentDto){
        Student created = studentService.save(mapper.map(studentDto, Student.class));
        return new ResponseEntity<>(mapper.map(created, SentStudentDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an student")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) throws StudentNotFoundException {
        Student deleted = studentService.delete(id);
        return new ResponseEntity<>(mapper.map(deleted,SentStudentDto.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find student by id")
    public ResponseEntity<?> findStudentById(@PathVariable long id) throws StudentNotFoundException {
        Student findById = studentService.findById(id);
        return new ResponseEntity<>(mapper.map(findById,SentStudentDto.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updating an existing student")
    public ResponseEntity<?> updateStudent(@PathVariable long id, @Valid @RequestBody RequestStudentDto studentDto) throws StudentNotFoundException {
        Student updateStudent = studentService.update(mapper.map(studentDto, Student.class), id);
        return new ResponseEntity<>(mapper.map(updateStudent, SentStudentDto.class), HttpStatus.OK);
    }


}
