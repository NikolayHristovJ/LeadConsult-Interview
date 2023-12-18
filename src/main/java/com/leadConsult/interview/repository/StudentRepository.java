package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends ListCrudRepository<Student,Long> {

  @Query(value = "SELECT s.* FROM students AS s " +
                 "LEFT JOIN students_courses AS sc ON s.student_id = sc.student_id " +
                 "LEFT JOIN courses AS cu ON sc.course_id = cu.course_id " +
                 "WHERE s.age >= :age AND cu.course_id = :courseId", nativeQuery = true)
  List<Student> findAllByStudentsInCourseAndAboveAge(@Param("courseId") Long courseId,@Param("age") int age);
}
