package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Enrollment;
import fcu.pbiecs.springdemo.model.EnrollmentId;
import fcu.pbiecs.springdemo.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // public Enrollment getEnrollmentById(EnrollmentId id) {
    //     Optional<Enrollment> optEnrollment = enrollmentRepository.findById(id);
    //     return optEnrollment.orElse(null);
    // }

    public List<Enrollment> getEnrollmentsByStudentId(Integer studentId) {
        return enrollmentRepository.findByIdStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourseId(Integer courseId) {
        return enrollmentRepository.findByIdCourseId(courseId);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        // In a real application, you might want to add validation
        // to ensure the student and course exist before saving.
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(EnrollmentId id, Enrollment enrollmentDetails) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentDetails.setId(id);
            return enrollmentRepository.save(enrollmentDetails);
        }
        return null;
    }

    public boolean deleteEnrollmentById(EnrollmentId id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
