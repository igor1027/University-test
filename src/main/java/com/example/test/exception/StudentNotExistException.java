package com.example.test.exception;

public class StudentNotExistException extends Exception{

    public StudentNotExistException(String message){
        super(message);
    }

    public StudentNotExistException(String firstName, String lastName){
        super(String.format("The student with first name and last name %d is not exist", firstName,lastName));
    }
}
