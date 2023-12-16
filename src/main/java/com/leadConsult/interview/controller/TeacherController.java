package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.service.StudentService;
import com.leadConsult.interview.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
