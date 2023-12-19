package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.CourseRequest;
import com.leadConsult.interview.dto.response.CourseResponse;
import com.leadConsult.interview.entity.Course;
import com.leadConsult.interview.mapper.CourseMapper;
import com.leadConsult.interview.repository.CourseRepository;
import com.leadConsult.interview.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

  private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  @Autowired
  public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
  }

  @Override
  public Course getCourseFromRepository(Long courseId) {
    return courseRepository.findById(courseId)
                         .orElseThrow(
                           () -> new EntityNotFoundException(String.format("Course not found with id: %s", courseId)));
  }

  @Override
  public List<CourseResponse> getAllCourses() {
    log.info("Fetching all courses");
    List<Course> courses = courseRepository.findAll();
    return courseMapper.listCourseToListCourseResponse(courses);
  }

  @Override
  public CourseResponse postGroup(CourseRequest courseRequest) {
    log.info("Saving course to DB");
    Course course = courseMapper.courseRequestToCourse(courseRequest);
    courseRepository.save(course);

    return courseMapper.courseToCourseResponse(course);
  }

  @Override
  public CourseResponse getCourseById(Long courseId) {
    log.info(String.format("Fetching course with id: %s", courseId));
    Course course = getCourseFromRepository(courseId);

    return courseMapper.courseToCourseResponse(course);
  }

  @Override
  @Transactional
  public CourseResponse editCourse(Long courseId, CourseRequest request) {
    log.info(String.format("Editing course with id: %s", courseId));
    Course oldCourse = getCourseFromRepository(courseId);

    Course editedCourse = courseMapper.courseRequestToCourse(request);
    editedCourse.setCourseId(courseId);
    editedCourse.setStudents(oldCourse.getStudents());
    editedCourse.setTeachers(oldCourse.getTeachers());
    CourseResponse response = courseMapper.courseToCourseResponse(oldCourse);
    courseRepository.save(editedCourse);

    return response;
  }

  @Override
  public List<CourseResponse> getCoursesByType(String courseType) {
    log.info(String.format("Fetching course with type: %s", courseType));
    List<Course> courses = courseRepository.findAllByType(courseType);
    return courseMapper.listCourseToListCourseResponse(courses);
  }

}
