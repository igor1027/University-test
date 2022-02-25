package com.example.test.exception;

import java.time.DayOfWeek;

public class DayOfWeekNotFoundException extends Exception{

    public DayOfWeekNotFoundException(String message){
        super(message);
    }

    public DayOfWeekNotFoundException(DayOfWeek dayOfWeek){
        super(String.format("No schedule for that %d is not found", dayOfWeek));
    }
}
