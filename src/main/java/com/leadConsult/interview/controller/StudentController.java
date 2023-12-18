package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/v1/api/students")
@Validated
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
  public ResponseEntity<StudentResponse> postStudent(@RequestBody @Valid StudentRequest studentRequest){
    StudentResponse newStudent = studentService.postStudent(studentRequest);

    return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
  }

  @GetMapping("/{studentId}")
  public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long studentId){
    StudentResponse response = studentService.getStudentById(studentId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{studentId}")
  public ResponseEntity<StudentResponse> editStudent(@RequestBody @Valid StudentRequest request,
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

  @DeleteMapping("{studentId}")
  public ResponseEntity<StudentResponse> deleteStudent(@PathVariable Long studentId, @RequestParam(required = false) boolean returnOld){
    StudentResponse response = studentService.deleteStudent(studentId);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @GetMapping("/courses/{courseId}")
  public ResponseEntity<List<StudentResponse>> getAllStudentsInCourseAndAboveAge(@PathVariable Long courseId,
                                                                                 @RequestParam @Positive(message = "age should be above 0") int age){
    List<StudentResponse> response = studentService.getAllStudentsInCourseAndAboveAge(courseId,age);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/groups/{groupId}")
  public ResponseEntity<List<StudentResponse>> getAllStudentsByGroup(@PathVariable Long groupId){
    List<StudentResponse> response = studentService.getStudentByGroup(groupId);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/courses/{courseId}/groups/{groupId}")
  public ResponseEntity<List<StudentResponse>> getAllStudentsByCourseAndGroup(@PathVariable Long courseId, @PathVariable Long groupId){
    List<StudentResponse> response = studentService.getStudentByCourseAndGroup(courseId,groupId);
    return ResponseEntity.ok(response);
  }

}
