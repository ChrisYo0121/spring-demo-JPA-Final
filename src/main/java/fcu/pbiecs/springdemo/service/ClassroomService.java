package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Classroom;
import fcu.pbiecs.springdemo.repository.ClassroomRepository;
import fcu.pbiecs.springdemo.repository.CourseScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private CourseScheduleRepository courseScheduleRepository;

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findById(Integer id) {
        return classroomRepository.findById(id);
    }

    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Transactional
    public void deleteById(Integer id) {
        // 先刪除參照到此教室的課程表
        courseScheduleRepository.deleteByClassroomClassroomId(id);
        // 再刪除教室
        classroomRepository.deleteById(id);
    }
}
