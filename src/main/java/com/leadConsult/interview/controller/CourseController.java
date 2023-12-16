package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.CourseRequest;
import com.leadConsult.interview.dto.response.CourseResponse;
import com.leadConsult.interview.service.CourseService;
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
@RequestMapping("/v1/api/courses")
public class CourseController {
  private final CourseService courseService;

  @Autowired
  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public ResponseEntity<List<CourseResponse>> getAllCourses(){
    List<CourseResponse> response = courseService.getAllCourses();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<CourseResponse> postCourse(@RequestBody CourseRequest courseRequest){
    CourseResponse response = courseService.postGroup(courseRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
