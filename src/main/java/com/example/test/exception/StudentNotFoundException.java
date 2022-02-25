package com.example.test.exception;

public class StudentNotFoundException extends Exception {

    public StudentNotFoundException(String message){
        super(message);
    }
    public StudentNotFoundException(Long id){
        super(String.format("The student with id %d is not found", id));
    }
}
