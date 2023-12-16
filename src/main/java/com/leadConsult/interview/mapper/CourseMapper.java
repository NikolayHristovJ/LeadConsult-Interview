package com.leadConsult.interview.mapper;

import com.leadConsult.interview.dto.request.CourseRequest;
import com.leadConsult.interview.dto.response.CourseResponse;
import com.leadConsult.interview.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
  Course courseRequestToCourse(CourseRequest request);
  CourseResponse courseToCourseResponse (Course course);
  List<CourseResponse> listCourseToListCourseResponse (List<Course> courses);
}
