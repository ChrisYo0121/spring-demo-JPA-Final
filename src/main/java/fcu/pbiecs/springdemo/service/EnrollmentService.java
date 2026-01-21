package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Enrollment;
import fcu.pbiecs.springdemo.model.EnrollmentId;
import fcu.pbiecs.springdemo.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getEnrollmentsByStudentId(Integer studentId) {
        return enrollmentRepository.findByIdStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourseId(Integer courseId) {
        return enrollmentRepository.findByIdCourseId(courseId);
    }

    public Enrollment getEnrollmentById(EnrollmentId id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(EnrollmentId id, Enrollment enrollmentDetails) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setEnrollment_date(enrollmentDetails.getEnrollment_date());
            enrollment.setGrade(enrollmentDetails.getGrade());
            return enrollmentRepository.save(enrollment);
        }).orElse(null);
    }

    @Transactional
    public boolean deleteEnrollmentById(EnrollmentId id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}