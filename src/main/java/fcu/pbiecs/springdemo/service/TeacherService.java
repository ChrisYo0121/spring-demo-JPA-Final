package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Teacher;
import fcu.pbiecs.springdemo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Integer id) {
        Optional<Teacher> optTeacher = teacherRepository.findById(id);
        return optTeacher.orElse(null);
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacherDetails) {
        // 根據傳入的 teacher 物件中的 ID 檢查是否存在
        if (teacherRepository.existsById(teacherDetails.getTeacherId())) {
            return teacherRepository.save(teacherDetails);
        }
        return null;
    }

    public boolean deleteTeacherById(Integer id) {
        if (teacherRepository.existsById(id)) {
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