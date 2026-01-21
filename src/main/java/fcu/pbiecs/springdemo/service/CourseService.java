package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Course;
import fcu.pbiecs.springdemo.repository.CourseRepository;
import fcu.pbiecs.springdemo.repository.CourseScheduleRepository;
import fcu.pbiecs.springdemo.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseScheduleRepository courseScheduleRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        if (course.getCourseId() != null && courseRepository.existsById(course.getCourseId())) {
            return courseRepository.save(course);
        }
        return null;
    }

    @Transactional
    public boolean deleteCourseById(Integer id) {
        if (courseRepository.existsById(id)) {
            // 1. 刪除該課程的所有選課紀錄
            enrollmentRepository.deleteByIdCourseId(id);

            // 2. 刪除該課程的所有課程表
            courseScheduleRepository.deleteByCourseCourseId(id);

            // 3. 刪除課程本體
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}