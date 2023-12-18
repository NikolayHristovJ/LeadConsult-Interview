package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.service.StudentService;
import com.leadConsult.interview.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

  @Operation(
    description = "Add a teacher to the DB",
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
  public ResponseEntity<TeacherResponse> postTeacher(@RequestBody @Valid TeacherRequest teacherRequest){
    TeacherResponse newTeacher = teacherService.postTeacher(teacherRequest);

    return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
  }

  @GetMapping("/{teacherId}")
  public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long teacherId){
    TeacherResponse response = teacherService.getTeacherById(teacherId);
    return ResponseEntity.ok(response);
  }

  @Operation(
    description = "Edit a teacher",
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
        description = "Failure teacher id not found",
        responseCode = "404"
      ),
      @ApiResponse(
        description = "Failure you tried to edit with data that is not valid",
        responseCode = "400"
      )
    }
  )
  @PutMapping("/{teacherId}")
  public ResponseEntity<TeacherResponse> editTeacher(@RequestBody @Valid TeacherRequest request,
                                                    @PathVariable Long teacherId,
                                                    @RequestParam(required = false) boolean returnOld){
    TeacherResponse response = teacherService.editTeacher(teacherId,request);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @Operation(
    description = "Add a Course to a Teacher",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Failure no course/teacher found",
        responseCode = "404"
      )
    }
  )
  @PostMapping("/{teacherId}/courses/{courseId}/add")
  public ResponseEntity<Void> addCourseToStudent(@PathVariable Long courseId,@PathVariable Long teacherId){
    teacherService.addCourseToTeacher(courseId,teacherId);
    return ResponseEntity.noContent().build();
  }

  @Operation(
    description = "Delete Teacher",
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
        description = "Failure teacher id not found",
        responseCode = "404"
      )
    }
  )
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

  @Operation(
    description = "Get a Teacher that is in a specific course and a specific group",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      )
    }
  )
  @GetMapping("/courses/{courseId}/groups/{groupId}")
  public ResponseEntity<List<TeacherResponse>> getAllTeachersByCourseAndGroup(@PathVariable Long courseId, @PathVariable Long groupId){
    List<TeacherResponse> response = teacherService.getTeacherByCourseAndGroup(courseId,groupId);
    return ResponseEntity.ok(response);
  }
}
