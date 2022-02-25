package com.example.test.controller;


import com.example.test.dto.student.RequestStudentDto;
import com.example.test.dto.student.SentStudentDto;
import com.example.test.dto.subject.RequestSubjectDto;
import com.example.test.dto.subject.SentSubjectDto;
import com.example.test.dto.teacher.RequestTeacherDto;
import com.example.test.dto.teacher.SentTeacherDto;
import com.example.test.entity.Student;
import com.example.test.entity.Subject;
import com.example.test.entity.Teacher;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.exception.SubjectNotFoundException;
import com.example.test.exception.TeacherNotFoundException;
import com.example.test.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create")
    @Operation(summary = "Create new subject")
    public ResponseEntity<?> createSubject(@Valid @RequestBody RequestSubjectDto subjectDto){
        Subject created = subjectService.save(mapper.map(subjectDto, Subject.class));
        return new ResponseEntity<>(mapper.map(created, SentSubjectDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an subject")
    public ResponseEntity<?> deleteTeacher(@PathVariable long id) throws SubjectNotFoundException {
        Subject deleted = subjectService.delete(id);
        return new ResponseEntity<>(mapper.map(deleted,SentSubjectDto.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find subject by id")
    public ResponseEntity<?> findSubjectById(@PathVariable long id) throws SubjectNotFoundException {
        Subject findById = subjectService.findById(id);
        return new ResponseEntity<>(mapper.map(findById,SentSubjectDto.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updating an existing subject")
    public ResponseEntity<?> updateSubject(@PathVariable long id, @Valid @RequestBody RequestSubjectDto subjectDto) throws SubjectNotFoundException {
        Subject updateSubject = subjectService.update(mapper.map(subjectDto, Subject.class), id);
        return new ResponseEntity<>(mapper.map(updateSubject, SentSubjectDto.class), HttpStatus.OK);
    }


}
