package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<StudentResponse> postStudent(@RequestBody StudentRequest studentRequest){
    StudentResponse newStudent = studentService.postStudent(studentRequest);

    return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
  }

  @GetMapping("/{studentId}")
  public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long studentId){
    StudentResponse response = studentService.getStudentById(studentId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{studentId}")
  public ResponseEntity<StudentResponse> editStudent(@RequestBody StudentRequest request,
                                                    @PathVariable Long studentId,
                                                    @RequestParam(required = false) boolean returnOld){
    StudentResponse response = studentService.editStudent(studentId,request);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @PostMapping("/{studentId}/courses/{courseId}/add")
  public ResponseEntity<Void> addCourseToStudent(@PathVariable Long courseId,@PathVariable Long studentId){
    studentService.addCourseToStudent(courseId,studentId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{studentId}/groups/{groupId}/add")
  public ResponseEntity<Void> addStudentToGroup(@PathVariable Long studentId,@PathVariable Long groupId){
    studentService.addStudentToGroup(studentId,groupId);
    return ResponseEntity.noContent().build();
  }

}
