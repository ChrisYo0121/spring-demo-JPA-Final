package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.CourseSchedule;
import fcu.pbiecs.springdemo.repository.CourseScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseScheduleService {

    @Autowired
    private CourseScheduleRepository courseScheduleRepository;

    public List<CourseSchedule> findAll() {
        return courseScheduleRepository.findAll();
    }

    public Optional<CourseSchedule> findById(Integer id) {
        return courseScheduleRepository.findById(id);
    }

    public CourseSchedule save(CourseSchedule schedule) {
        return courseScheduleRepository.save(schedule);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (courseScheduleRepository.existsById(id)) {
            courseScheduleRepository.deleteById(id);
        }
    }
}
