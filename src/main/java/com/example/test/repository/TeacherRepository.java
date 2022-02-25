package com.example.test.repository;

import com.example.test.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
