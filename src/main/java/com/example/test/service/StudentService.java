package com.example.test.service;

import com.example.test.entity.Student;
import com.example.test.exception.StudentNotExistException;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public Student findById(Long id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student delete(Long id) throws StudentNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
        return student;
    }

    public Student findByNameAndLastName(String firstName, String lastName)throws StudentNotExistException {
       return studentRepository.findByFirstNameAndLastName(firstName,lastName).orElseThrow(() -> new StudentNotExistException(firstName,lastName));
    }


    public Student update(Student student, long id) throws StudentNotFoundException{
      Student actualStudent = findById(id);
      Student newStudent = studentRepository.save(buildStudent(actualStudent, student));
      return  newStudent;
    }


    private Student buildStudent(Student actualStudent, Student newStudent){
        actualStudent.setFirstName(newStudent.getFirstName());
        actualStudent.setLastName(newStudent.getLastName());
        actualStudent.setMiddleName(newStudent.getMiddleName());
        actualStudent.setCourse(newStudent.getCourse());
        actualStudent.setNumberGroup(newStudent.getNumberGroup());
        return actualStudent;
    }
}