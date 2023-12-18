package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Course;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends ListCrudRepository<Course, Long> {

  List<Course> findAllByType(String courseType);

}

