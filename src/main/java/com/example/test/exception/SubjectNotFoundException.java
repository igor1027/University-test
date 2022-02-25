package com.example.test.exception;

public class SubjectNotFoundException extends Exception{

    public SubjectNotFoundException(String message){
        super(message);
    }

    public SubjectNotFoundException(Long id){
        super(String.format("The subject with id %d is not found", id));
    }
}
