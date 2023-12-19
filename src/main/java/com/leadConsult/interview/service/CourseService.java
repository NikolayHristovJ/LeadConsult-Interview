package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.CourseRequest;
import com.leadConsult.interview.dto.response.CourseResponse;
import com.leadConsult.interview.entity.Course;

import java.util.List;

public interface CourseService {

  /**
   * Fetches a course based on the id given
   *
   * @param courseId
   * @return Course
   */
  Course getCourseFromRepository(Long courseId);

  /**
   * Fetches all courses from the DB
   *
   * @return list of Course Responses
   */
  List<CourseResponse> getAllCourses();

  /**
   * Saves a course based on the request class given
   *
   * @param courseRequest
   * @return CourseResponse
   */
  CourseResponse postGroup(CourseRequest courseRequest);

  /**
   * Fetches course based on the id given if the course is not found an EntityNotFoundException will be thrown
   *
   * @param courseId
   * @return CourseResponse
   */
  CourseResponse getCourseById(Long courseId);

  /**
   * Edits a course based on the request class given and the id given if the course is not found an EntityNotFoundException will be thrown.
   * Returns the old version of the course
   *
   * @param courseId
   * @param request
   * @return CourseResponse
   */
  CourseResponse editCourse(Long courseId, CourseRequest request);

  /**
   * Fetches all courses based on the type given (main/secondary)
   *
   * @param courseType
   * @return list of Course Responses
   */
  List<CourseResponse> getCoursesByType(String courseType);
}
