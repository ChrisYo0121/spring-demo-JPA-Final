package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Course;
import fcu.pbiecs.springdemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "課程管理", description = "提供課程 CRUD API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Operation(summary = "查詢所有課程", description = "取得所有課程的資訊")
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @Operation(summary = "查詢課程By ID", description = "依照ID查詢課程資訊")
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @Operation(summary = "刪除課程", description = "依照ID刪除課程")
    @DeleteMapping("/{id}")
    public String deleteCourseById(@PathVariable Integer id) {
        boolean deleted = courseService.deleteCourseById(id);
        if (deleted) {
            return "Course with ID " + id + " deleted successfully.";
        } else {
            return "Course with ID " + id + " not found.";
        }
    }

    @Operation(summary = "新增課程", description = "新增一門課程")
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @Operation(summary = "更新課程", description = "依照ID更新課程資訊")
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Integer id, @RequestBody Course courseDetails) {
        return courseService.updateCourse(id, courseDetails);
    }
}
