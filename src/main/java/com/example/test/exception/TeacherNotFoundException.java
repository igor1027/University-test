package com.example.test.exception;

public class TeacherNotFoundException extends Exception{

    public TeacherNotFoundException(String message){
        super(message);
    }

    public TeacherNotFoundException(Long id){
        super(String.format("The teacher with id %d is not found", id));
    }
}
