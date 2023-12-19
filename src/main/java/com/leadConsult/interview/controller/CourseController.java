package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.CourseRequest;
import com.leadConsult.interview.dto.response.CourseResponse;
import com.leadConsult.interview.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
@RequestMapping("/v1/api/courses")
public class CourseController {

  private final CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public ResponseEntity<List<CourseResponse>> getAllCourses() {
    List<CourseResponse> response = courseService.getAllCourses();
    return ResponseEntity.ok(response);
  }

  @Operation(
    description = "Add a Course to the DB",
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
  public ResponseEntity<CourseResponse> postCourse(@RequestBody @Valid CourseRequest courseRequest) {
    CourseResponse response = courseService.postGroup(courseRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/{courseId}")
  public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId) {
    CourseResponse response = courseService.getCourseById(courseId);
    return ResponseEntity.ok(response);
  }

  @Operation(
    description = "Edit a Course",
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
        description = "Failure course id not found",
        responseCode = "404"
      ),
      @ApiResponse(
        description = "Failure you tried to edit with data that is not valid",
        responseCode = "400"
      )
    }
  )
  @PutMapping("/{courseId}")
  public ResponseEntity<CourseResponse> editCourse(@RequestBody @Valid CourseRequest request,
                                                   @PathVariable Long courseId,
                                                   @RequestParam(required = false) boolean returnOld) {
    CourseResponse response = courseService.editCourse(courseId, request);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @Operation(
    description = "Get all Courses that are have a specific type",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      )
    }
  )
  @GetMapping("/type/{courseType}")
  public ResponseEntity<List<CourseResponse>> getCoursesByType(@PathVariable String courseType) {
    List<CourseResponse> response = courseService.getCoursesByType(courseType);
    return ResponseEntity.ok(response);
  }
}
