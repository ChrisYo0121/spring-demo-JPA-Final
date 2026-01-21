package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.CourseSchedule;
import fcu.pbiecs.springdemo.service.CourseScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "課程表管理", description = "提供課程表 CRUD API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/course-schedules")
public class CourseScheduleController {

    @Autowired
    private CourseScheduleService courseScheduleService;

    @Operation(summary = "查詢所有課程表", description = "取得所有課程表的列表")
    @GetMapping
    public List<CourseSchedule> getAllCourseSchedules() {
        return courseScheduleService.findAll();
    }

    @Operation(summary = "依 ID 查詢課程表", description = "使用課程表 ID 取得特定課程表的詳細資訊")
    @GetMapping("/{id}")
    public ResponseEntity<CourseSchedule> getCourseScheduleById(@PathVariable(value = "id") Integer scheduleId) {
        return courseScheduleService.findById(scheduleId)
                .map(schedule -> ResponseEntity.ok().body(schedule))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "新增課程表", description = "建立一筆新的課程表紀錄")
    @PostMapping
    public CourseSchedule createCourseSchedule(@RequestBody CourseSchedule courseSchedule) {
        return courseScheduleService.save(courseSchedule);
    }

    @Operation(summary = "更新課程表資訊", description = "依據課程表 ID 更新現有的課程表資訊")
    @PutMapping("/{id}")
    public ResponseEntity<CourseSchedule> updateCourseSchedule(@PathVariable(value = "id") Integer scheduleId,
                                                             @RequestBody CourseSchedule scheduleDetails) {
        return courseScheduleService.findById(scheduleId)
                .map(schedule -> {
                    schedule.setCourse(scheduleDetails.getCourse());
                    schedule.setTeacher(scheduleDetails.getTeacher());
                    schedule.setClassroom(scheduleDetails.getClassroom());
                    schedule.setDayOfWeek(scheduleDetails.getDayOfWeek());
                    schedule.setStartTime(scheduleDetails.getStartTime());
                    schedule.setEndTime(scheduleDetails.getEndTime());
                    CourseSchedule updatedSchedule = courseScheduleService.save(schedule);
                    return ResponseEntity.ok().body(updatedSchedule);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "刪除課程表", description = "依據課程表 ID 刪除一筆課程表紀錄")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseSchedule(@PathVariable(value = "id") Integer scheduleId) {
        return courseScheduleService.findById(scheduleId)
                .map(schedule -> {
                    courseScheduleService.deleteById(scheduleId);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
