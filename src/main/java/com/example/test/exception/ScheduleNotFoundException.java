package com.example.test.exception;

public class ScheduleNotFoundException extends Exception{

    public ScheduleNotFoundException(String message){
        super(message);
    }

    public ScheduleNotFoundException(Long id){
        super(String.format("The schedule with id %d is not found", id));
    }
}
