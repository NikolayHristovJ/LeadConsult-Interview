package com.leadConsult.interview.repository;

import com.leadConsult.interview.entity.Student;
import org.springframework.data.repository.ListCrudRepository;

public interface StudentRepository extends ListCrudRepository<Student,Long> {

}
