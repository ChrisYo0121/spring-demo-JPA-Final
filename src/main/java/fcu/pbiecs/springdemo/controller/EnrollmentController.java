package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Enrollment;
import fcu.pbiecs.springdemo.model.EnrollmentId;
import fcu.pbiecs.springdemo.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "選課管理", description = "提供選課紀錄 CRUD API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Operation(summary = "查詢所有選課紀錄", description = "取得所有選課紀錄")
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @Operation(summary = "查詢特定選課紀錄", description = "依照學生ID和課程ID查詢選課紀錄")
    @GetMapping("/students/{studentId}/courses/{courseId}")
    public Enrollment getEnrollmentById(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        return enrollmentService.getEnrollmentById(enrollmentId);
    }

    @Operation(summary = "查詢特定學生的所有選課紀錄", description = "給定 student id，查詢該名學生的所有選課紀錄")
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudentId(@PathVariable Integer studentId) {
        return enrollmentService.getEnrollmentsByStudentId(studentId);
    }

    @Operation(summary = "查詢特定課程的所有選課學生", description = "給定 course id，查詢該課程的所有選課學生")
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getEnrollmentsByCourseId(@PathVariable Integer courseId) {
        return enrollmentService.getEnrollmentsByCourseId(courseId);
    }

    @Operation(summary = "新增選課紀錄", description = "新增一筆選課紀錄")
    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        // The request body should contain the id object with student_id and course_id
        // as well as enrollment_date and grade.
        // e.g., { "id": { "student_id": 1, "course_id": 101 }, "enrollment_date": "2025-08-27", "grade": "A" }
        return enrollmentService.createEnrollment(enrollment);
    }

    @Operation(summary = "更新選課紀錄", description = "依照學生ID和課程ID更新選課紀錄")
    @PutMapping("/students/{studentId}/courses/{courseId}")
    public Enrollment updateEnrollment(@PathVariable Integer studentId, @PathVariable Integer courseId, @RequestBody Enrollment enrollmentDetails) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        return enrollmentService.updateEnrollment(enrollmentId, enrollmentDetails);
    }

    @Operation(summary = "刪除選課紀錄", description = "依照學生ID和課程ID刪除選課紀錄")
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public String deleteEnrollmentById(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        boolean deleted = enrollmentService.deleteEnrollmentById(enrollmentId);
        if (deleted) {
            return "Enrollment for student " + studentId + " and course " + courseId + " deleted successfully.";
        } else {
            return "Enrollment not found.";
        }
    }
}
