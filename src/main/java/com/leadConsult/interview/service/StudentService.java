package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {

  List<StudentResponse> getAllStudents();

  StudentResponse postStudent(StudentRequest studentRequest);
}
