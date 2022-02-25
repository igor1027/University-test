package com.example.test.exception;

public class TeacherNotExistException extends Exception {

    public TeacherNotExistException(String message){
        super(message);
    }

    public TeacherNotExistException(String firstName, String lastName){
        super(String.format("The teacher with first name and last name %d is not exist", firstName,lastName));
    }
}
