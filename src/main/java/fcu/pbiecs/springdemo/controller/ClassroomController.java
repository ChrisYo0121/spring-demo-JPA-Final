package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Classroom;
import fcu.pbiecs.springdemo.service.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "教室管理", description = "提供教室 CRUD API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Operation(summary = "查詢所有教室", description = "取得所有教室的列表")
    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.findAll();
    }

    @Operation(summary = "依 ID 查詢教室", description = "使用教室 ID 取得特定教室的詳細資訊")
    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable(value = "id") Integer classroomId) {
        return classroomService.findById(classroomId)
                .map(classroom -> ResponseEntity.ok().body(classroom))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "新增教室", description = "建立一筆新的教室紀錄")
    @PostMapping
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return classroomService.save(classroom);
    }

    @Operation(summary = "更新教室資訊", description = "依據教室 ID 更新現有的教室資訊")
    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable(value = "id") Integer classroomId,
                                                   @RequestBody Classroom classroomDetails) {
        return classroomService.findById(classroomId)
                .map(classroom -> {
                    classroom.setClassroomName(classroomDetails.getClassroomName());
                    classroom.setCapacity(classroomDetails.getCapacity());
                    Classroom updatedClassroom = classroomService.save(classroom);
                    return ResponseEntity.ok().body(updatedClassroom);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "刪除教室", description = "依據教室 ID 刪除一筆教室紀錄")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable(value = "id") Integer classroomId) {
        return classroomService.findById(classroomId)
                .map(classroom -> {
                    classroomService.deleteById(classroomId);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
