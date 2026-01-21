package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Course;
import fcu.pbiecs.springdemo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id) {
        Optional<Course> optCourse = courseRepository.findById(id);
        return optCourse.orElse(null);
    }

    public Course createCourse(Course course) {
        course.setCourse_id(null);
        return courseRepository.save(course);
    }

    public Course updateCourse(Integer id, Course courseDetails) {
        if (courseRepository.existsById(id)) {
            courseDetails.setCourse_id(id);
            return courseRepository.save(courseDetails);
        }
        return null;
    }

    public boolean deleteCourseById(Integer id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
