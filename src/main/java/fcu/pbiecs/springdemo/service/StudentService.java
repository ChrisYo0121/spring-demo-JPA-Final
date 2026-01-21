package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Student;
import fcu.pbiecs.springdemo.repository.EnrollmentRepository;
import fcu.pbiecs.springdemo.repository.StudentRepository;
import fcu.pbiecs.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        Optional<Student> optStudent = studentRepository.findById(id);
        return optStudent.orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student studentDetails) {
        if (studentRepository.existsById(studentDetails.getStudentId())) {
            return studentRepository.save(studentDetails);
        }
        return null;
    }

    @Transactional
    public boolean deleteStudentById(Integer id) {
        if (studentRepository.existsById(id)) {
            // 1. 刪除該學生的選課紀錄 (Enrollment)
            enrollmentRepository.deleteByIdStudentId(id);

            // 2. 刪除該學生的使用者帳號 (User)
            userRepository.deleteByStudentStudentId(id);

            // 3. 刪除學生本體 (Student)
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}