package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Student;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ListCrudRepository<Student,Long> {

}
