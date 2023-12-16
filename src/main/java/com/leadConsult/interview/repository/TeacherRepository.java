package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Teacher;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends ListCrudRepository<Teacher, Long> {

}
