package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.CourseRequest;
import com.leadConsult.interview.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

  List<CourseResponse> getAllCourses();

  CourseResponse postGroup(CourseRequest courseRequest);
}
