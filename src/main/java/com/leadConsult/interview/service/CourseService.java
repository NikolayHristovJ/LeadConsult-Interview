package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.CourseRequest;
import com.leadConsult.interview.dto.response.CourseResponse;
import com.leadConsult.interview.entity.Course;

import java.util.List;

public interface CourseService {

  Course getCourseFromRepository(Long courseId);

  List<CourseResponse> getAllCourses();

  CourseResponse postGroup(CourseRequest courseRequest);

  CourseResponse getCourseById(Long courseId);
}
