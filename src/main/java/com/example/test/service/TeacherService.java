package com.example.test.service;

import com.example.test.entity.Student;
import com.example.test.entity.Teacher;
import com.example.test.exception.StudentNotFoundException;
import com.example.test.exception.TeacherNotExistException;
import com.example.test.exception.TeacherNotFoundException;
import com.example.test.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher delete(Long id) throws TeacherNotFoundException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        teacherRepository.delete(teacher);
        return teacher;
    }

    public Teacher findById(Long id) throws TeacherNotFoundException {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }


    public Teacher update(Teacher teacher, long id) throws TeacherNotFoundException {
        Teacher actualTeacher = findById(id);
        Teacher newTeacher = teacherRepository.save(buildTeacher(actualTeacher, teacher));
        return  newTeacher;
    }

    private Teacher buildTeacher(Teacher actualTeacher, Teacher newTeacher){
        actualTeacher.setFirstName(newTeacher.getFirstName());
        actualTeacher.setLastName(newTeacher.getLastName());
        actualTeacher.setMiddleName(newTeacher.getMiddleName());
        actualTeacher.setSubject(newTeacher.getSubject());
        return actualTeacher;
    }

}
