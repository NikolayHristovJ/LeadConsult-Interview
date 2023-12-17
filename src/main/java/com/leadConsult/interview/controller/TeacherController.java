package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.service.StudentService;
import com.leadConsult.interview.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/teachers")
public class TeacherController {
  private final TeacherService teacherService;
  @Autowired
  TeacherController(StudentService studentService, TeacherService teacherService){
    this.teacherService = teacherService;
  }

  @GetMapping
  public ResponseEntity<List<TeacherResponse>> getAllTeachers(){
    List<TeacherResponse> response = teacherService.getAllTeachers();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<TeacherResponse> postTeacher(@RequestBody TeacherRequest teacherRequest){
    TeacherResponse newTeacher = teacherService.postTeacher(teacherRequest);

    return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
  }

  @GetMapping("/{teacherId}")
  public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long teacherId){
    TeacherResponse response = teacherService.getTeacherById(teacherId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{teacherId}")
  public ResponseEntity<TeacherResponse> editTeacher(@RequestBody TeacherRequest request,
                                                    @PathVariable Long teacherId,
                                                    @RequestParam(required = false) boolean returnOld){
    TeacherResponse response = teacherService.editTeacher(teacherId,request);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping("/{teacherId}/courses/{courseId}/add")
  public ResponseEntity<Void> addCourseToStudent(@PathVariable Long courseId,@PathVariable Long teacherId){
    teacherService.addCourseToTeacher(courseId,teacherId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{teacherId}")
  public ResponseEntity<TeacherResponse> deleteTeacher(@PathVariable Long teacherId,@RequestParam(required = false) boolean returnOld){
    TeacherResponse response = teacherService.deleteTeacher(teacherId);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping("/{teacherId}/groups/{groupId}/add")
  public ResponseEntity<Void> addTeacherToGroup(@PathVariable Long teacherId,@PathVariable Long groupId){
    teacherService.addTeacherToGroup(teacherId,groupId);
    return ResponseEntity.noContent().build();
  }
}
