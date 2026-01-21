package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Teacher;
import fcu.pbiecs.springdemo.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/teachers")
@Tag(name = "教師管理", description = "提供教師 CRUD API")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Operation(summary = "查詢所有教師", description = "取得所有教師的資訊")
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @Operation(summary = "查詢教師By ID", description = "依照ID查詢教師資訊")
    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Integer id) {
        return teacherService.getTeacherById(id);
    }

    @Operation(summary = "刪除教師", description = "依照ID刪除教師")
    @DeleteMapping("/{id}")
    public String deleteTeacherById(@PathVariable Integer id) {
        boolean deleted = teacherService.deleteTeacherById(id);
        if (deleted) {
            return "Teacher with ID " + id + " deleted successfully.";
        } else {
            return "Teacher with ID " + id + " not found.";
        }
    }

    @Operation(summary = "新增教師", description = "新增一位教師")
    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @Operation(summary = "更新教師", description = "依照ID更新教師資訊")
    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacherDetails) {
        teacherDetails.setTeacherId(id);
        return teacherService.updateTeacher(teacherDetails);
    }

    @Operation(summary = "依姓名查詢教師", description = "透過教師姓名查詢教師資訊")
    @GetMapping("/search/name/{name}")
    public List<Teacher> findByName(@PathVariable String name) {
        return teacherService.findByName(name);
    }

    @Operation(summary = "依郵件查詢教師", description = "透過教師郵件查詢教師資訊")
    @GetMapping("/search/email/{email}")
    public Teacher findByEmail(@PathVariable String email) {
        return teacherService.findByEmail(email);

    }

    @Operation(summary = "依年齡查詢教師", description = "透過教師年齡查詢教師資訊")
    @GetMapping("/search/age/{age}")
    public List<Teacher> findByAge(@PathVariable String age) {
        return teacherService.findByAge(age);
    }
}
