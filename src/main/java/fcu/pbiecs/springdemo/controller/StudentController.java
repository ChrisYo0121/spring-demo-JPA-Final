package fcu.pbiecs.springdemo.controller;
import fcu.pbiecs.springdemo.model.Student;
import fcu.pbiecs.springdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@Tag(name = "學生管理", description = "提供學生 CRUD API")

@CrossOrigin("*")
@RestController
@RequestMapping("/api/students")

public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "查詢所有學生", description = "取得所有學生的資訊")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }



    @Operation(summary = "查詢學生By ID", description = "依照ID查詢學生資訊")
    @GetMapping ("/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        // 修正：改用 studentService 查詢學生 (2025-08-20)
        return studentService.getStudentById(id);
    }

    @Operation(summary = "刪除學生", description = "依照ID刪除學生")
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable Integer id) {
        // 修正：改用 studentService 刪除學生 (2025-08-20)
        boolean deleted = studentService.deleteStudentById(id);
        if (deleted) {
            return "Student with ID " + id + " deleted successfully.";
        } else {
            return "Student with ID " + id + " not found.";
        }
    }

    @Operation(summary = "新增學生", description = "新增一位學生")
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        // 修正：改用 studentService 新增學生 (2025-08-20)
        return studentService.createStudent(student);
    }

    @Operation(summary = "更新學生", description = "依照ID更新學生資訊")
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        // 修正：改用 studentService 更新學生 (2025-08-20)
        studentDetails.setStudentId(id);
        // 直接回傳 service 更新後的結果，避免再次查詢資料庫
        return studentService.updateStudent(studentDetails);
    }

    @Operation(summary = "查詢學生By First Name", description = "依照First Name查詢學生資訊")
    @GetMapping("/firstName/{firstName}")
    public List<Student> getStudentsByFirstName(@PathVariable String firstName) {
        // 修正：改用 studentService 查詢學生 (2025-08-20)
        return studentService.getStudentsByFirstName(firstName);
    }

    //查詢學生by last name
    @Operation(summary = "查詢學生By Last Name", description = "依照Last Name查詢學生資訊")
    @GetMapping("/lastName/{lastName}")
    public List<Student> getStudentsByLastName(@PathVariable String lastName) {
        // 修正：改用 studentService 查詢學生 (2025-08-20)
        return studentService.getStudentsByLastName(lastName);
    }

    //查詢學生by email
    @Operation(summary = "查詢學生By Email", description = "依照Email查詢學生資訊")
    @GetMapping("/email/{email}")
    public Student getStudentByEmail(@PathVariable String email) {
        // 修正：改用 studentService 查詢學生 (2025-08-20)
        return studentService.getStudentByEmail(email);
    }

}