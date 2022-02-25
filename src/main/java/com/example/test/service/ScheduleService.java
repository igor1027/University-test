package com.example.test.service;

import com.example.test.dto.schedule.FindScheduleRequest;
import com.example.test.entity.Schedule;
import com.example.test.entity.Student;
import com.example.test.entity.Teacher;
import com.example.test.exception.*;
import com.example.test.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StudentService studentService;



    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule findById(Long id) throws ScheduleNotFoundException{
        return scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
    }

    public Schedule findByDay(DayOfWeek dayOfWeek) throws DayOfWeekNotFoundException {
        return scheduleRepository.findByDayOfWeek(dayOfWeek).orElseThrow(() -> new DayOfWeekNotFoundException(dayOfWeek));

    }

    public Schedule deleteById(Long id) throws ScheduleNotFoundException {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
        scheduleRepository.delete(schedule);
        return schedule;
    }

    public Schedule deleteByDay(DayOfWeek dayOfWeek) throws DayOfWeekNotFoundException {
        Schedule byDayOfWeek = scheduleRepository.findByDayOfWeek(dayOfWeek).orElseThrow(() -> new DayOfWeekNotFoundException(dayOfWeek));
        scheduleRepository.delete(byDayOfWeek);
        return byDayOfWeek;
    }

    public Schedule update(Schedule schedule, long id) throws ScheduleNotFoundException {
        Schedule actualSchedhule = findById(id);
        Schedule newSchedule = scheduleRepository.save(buildSchedule(actualSchedhule, schedule));
        return newSchedule;
    }


    public List<Schedule> findScheduleForStudent(FindScheduleRequest request) throws StudentNotExistException {
       Student byNameAndLastName = studentService.findByNameAndLastName(request.getFirstName(),request.getLastName());
       List<Schedule> schedules = scheduleRepository.findByNumberGroupAndDayOfWeek(byNameAndLastName.getNumberGroup(), request.getDayOfWeek());
       if (schedules.isEmpty()){
           throw new RuntimeException(String.format("Schedule %s is not exist!", schedules));
       }
       return schedules;
    }


    private Schedule buildSchedule(Schedule actualSchedule, Schedule newSchedule){
        actualSchedule.setSubject(newSchedule.getSubject());
        actualSchedule.setNumberGroup(newSchedule.getNumberGroup());
        actualSchedule.setDayOfWeek(newSchedule.getDayOfWeek());
        return actualSchedule;
    }
}
