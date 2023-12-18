package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends ListCrudRepository<Teacher, Long> {

  @Query(value = "SELECT te.* FROM teachers AS te " +
                 "LEFT JOIN teachers_courses AS tc ON te.teacher_id = tc.teacher_id " +
                 "LEFT JOIN courses AS cu ON tc.course_id = cu.course_id " +
                 "WHERE te.group_id = :groupId AND cu.course_id = :courseId",nativeQuery = true)
  List<Teacher> findByCourseIdAndGroupId(Long courseId, Long groupId);
}
