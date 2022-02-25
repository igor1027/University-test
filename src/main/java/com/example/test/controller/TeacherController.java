package com.example.test.controller;

import com.example.test.dto.student.RequestStudentDto;
import com.example.test.dto.student.SentStudentDto;
import com.example.test.dto.teacher.RequestTeacherDto;
import com.example.test.dto.teacher.SentTeacherDto;
import com.example.test.entity.Student;
import com.example.test.entity.Teacher;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.exception.TeacherNotFoundException;
import com.example.test.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ModelMapper mapper;


    @PostMapping("/create")
    @Operation(summary = "Create new Teacher")
    public ResponseEntity<?> createTeacher(@Valid @RequestBody RequestTeacherDto teacherDto){
        Teacher created = teacherService.save(mapper.map(teacherDto, Teacher.class));
        return new ResponseEntity<>(mapper.map(created, SentTeacherDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an teacher")
    public ResponseEntity<?> deleteTeacher(@PathVariable long id) throws TeacherNotFoundException {
        Teacher deleted = teacherService.delete(id);
        return new ResponseEntity<>(mapper.map(deleted,SentTeacherDto.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find teacher by id")
    public ResponseEntity<?> findTeacherById(@PathVariable long id) throws TeacherNotFoundException {
        Teacher findById = teacherService.findById(id);
        return new ResponseEntity<>(mapper.map(findById,SentTeacherDto.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updating an existing teacher")
    public ResponseEntity<?> updateTeacher(@PathVariable long id, @Valid @RequestBody RequestTeacherDto teacherDto) throws TeacherNotFoundException {
        Teacher updateTeacher = teacherService.update(mapper.map(teacherDto, Teacher.class), id);
        return new ResponseEntity<>(mapper.map(updateTeacher, SentTeacherDto.class), HttpStatus.OK);
    }

}
