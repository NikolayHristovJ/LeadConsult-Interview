package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;

import java.util.List;

public interface StudentService {

  Student getStudentFromRepository(Long studentId);

  List<StudentResponse> getAllStudents();

  StudentResponse postStudent(StudentRequest studentRequest);

  StudentResponse getStudentById(Long studentId);

  StudentResponse editStudent(Long studentId, StudentRequest request);

  void addCourseToStudent(Long courseId, Long studentId);

  void addStudentToGroup(Long studentId, Long groupId);

  StudentResponse deleteStudent(Long studentId);
}
