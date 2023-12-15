package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Course;
import org.springframework.data.repository.ListCrudRepository;

public interface CourseRepository extends ListCrudRepository<Course, Long> {

}
