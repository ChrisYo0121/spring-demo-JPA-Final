package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Course;
import fcu.pbiecs.springdemo.model.Teacher;
import fcu.pbiecs.springdemo.repository.CourseRepository;
import fcu.pbiecs.springdemo.repository.CourseScheduleRepository;
import fcu.pbiecs.springdemo.repository.EnrollmentRepository;
import fcu.pbiecs.springdemo.repository.TeacherRepository;
import fcu.pbiecs.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseScheduleRepository courseScheduleRepository;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        if (teacherRepository.existsById(teacher.getTeacherId())) {
            return teacherRepository.save(teacher);
        }
        return null;
    }

    @Transactional
    public boolean deleteTeacherById(Integer id) {
        if (teacherRepository.existsById(id)) {
            // 1. 刪除該老師相關的課程表
            courseScheduleRepository.deleteByTeacherTeacherId(id);

            // 2. 處理該老師開設的課程 (需要先刪除選課紀錄，再刪除課程)
            List<Course> courses = courseRepository.findByTeacherId(id);
            for (Course course : courses) {
                // 刪除該課程的選課紀錄
                enrollmentRepository.deleteByIdCourseId(course.getCourseId());
                // (課程表在上面步驟1已經依據 TeacherId 刪除了，但為了保險起見，也可以依據 CourseId 刪除)
                courseScheduleRepository.deleteByCourseCourseId(course.getCourseId());
                // 刪除課程
                courseRepository.delete(course);
            }

            // 3. 刪除該老師的使用者帳號
            userRepository.deleteByTeacherTeacherId(id);

            // 4. 刪除老師本體
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Teacher> findByName(String name) {
        return teacherRepository.findByName(name);
    }

    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public List<Teacher> findByAge(String age) {
        return teacherRepository.findByAge(age);
    }
}
