package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;
import com.leadConsult.interview.service.StudentService;
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
@RequestMapping("/v1/api/students")
public class StudentController {
  private final StudentService studentService;
  @Autowired
  StudentController(StudentService studentService){
    this.studentService = studentService;
  }
  @GetMapping
  public ResponseEntity<List<StudentResponse>> getAllStudents(){
    List<StudentResponse> response = studentService.getAllStudents();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<Student> postStudent(@RequestBody StudentRequest studentRequest){
    Student newStudent = studentService.postStudent(studentRequest);

    return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
  }

}
