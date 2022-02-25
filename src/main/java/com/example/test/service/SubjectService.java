package com.example.test.service;

import com.example.test.entity.Subject;
import com.example.test.entity.Teacher;
import com.example.test.exception.SubjectNotFoundException;
import com.example.test.exception.TeacherNotFoundException;
import com.example.test.repository.SubjectRepository;
import com.example.test.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject delete(Long id) throws SubjectNotFoundException {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException(id));
        subjectRepository.delete(subject);
        return subject;
    }

    public Subject findById(Long id) throws SubjectNotFoundException {
        return subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException(id));
    }

    public Subject update(Subject subject, long id) throws SubjectNotFoundException {
        Subject actualSubject = findById(id);
        Subject newSubject = subjectRepository.save(buildSubject(actualSubject, subject));
        return  newSubject;
    }


    private Subject buildSubject(Subject actualSubject, Subject newSubject){
        actualSubject.setName(newSubject.getName());
        actualSubject.setRoomNumber(newSubject.getRoomNumber());
        return actualSubject;
    }
}
