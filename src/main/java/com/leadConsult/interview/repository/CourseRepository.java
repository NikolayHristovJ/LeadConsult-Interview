package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Course;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ListCrudRepository<Course, Long> {

}
