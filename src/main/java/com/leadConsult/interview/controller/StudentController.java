package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

  @Operation(
    description = "Add a student to the DB",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Failure you tried to post with data that is not valid",
        responseCode = "400"
      )
    }
  )
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

  @Operation(
    description = "Edit a student",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Success but with no body",
        responseCode = "204"
      ),
      @ApiResponse(
        description = "Failure student id not found",
        responseCode = "404"
      ),
      @ApiResponse(
        description = "Failure you tried to edit with data that is not valid",
        responseCode = "400"
      )
    }
  )
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

  @Operation(
    description = "Add a Course to a Student",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Failure no course/student found",
        responseCode = "404"
      )
    }
  )
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

  @Operation(
    description = "Delete Student",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Success but with no body",
        responseCode = "204"
      ),
      @ApiResponse(
        description = "Failure student id not found",
        responseCode = "404"
      )
    }
  )
  @DeleteMapping("{studentId}")
  public ResponseEntity<StudentResponse> deleteStudent(@PathVariable Long studentId, @RequestParam(required = false) boolean returnOld){
    StudentResponse response = studentService.deleteStudent(studentId);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @Operation(
    description = "Get a Student that is in a specific Course and is above a certain age",
    responses = {
      @ApiResponse(
        description = "Success if age is not present it will print all Students in that course",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Failure if age is bellow 0",
        responseCode = "400"
      )
    }
  )
  @GetMapping("/courses/{courseId}")
  public ResponseEntity<List<StudentResponse>> getAllStudentsInCourseAndAboveAge(@PathVariable Long courseId,
                                                                                 @RequestParam(required = false) @Positive(message = "age should be above 0") Integer age){
    final List<StudentResponse> response;

    if (age != null){
      response = studentService.getAllStudentsInCourseAndAboveAge(courseId, age);
    }else {
      response = studentService.getAllStudentsInCourse(courseId);
    }

    return ResponseEntity.ok(response);
  }

  @Operation(
    description = "Get a Student that is in a specific group",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      )
    }
  )
  @GetMapping("/groups/{groupId}")
  public ResponseEntity<List<StudentResponse>> getAllStudentsByGroup(@PathVariable Long groupId){
    List<StudentResponse> response = studentService.getStudentByGroup(groupId);

    return ResponseEntity.ok(response);
  }

  @Operation(
    description = "Get a Student that is in a specific course and a specific group",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      )
    }
  )
  @GetMapping("/courses/{courseId}/groups/{groupId}")
  public ResponseEntity<List<StudentResponse>> getAllStudentsByCourseAndGroup(@PathVariable Long courseId, @PathVariable Long groupId){
    List<StudentResponse> response = studentService.getStudentByCourseAndGroup(courseId,groupId);
    return ResponseEntity.ok(response);
  }

}
