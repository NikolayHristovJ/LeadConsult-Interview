package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;
import com.leadConsult.interview.mapper.StudentMapper;
import com.leadConsult.interview.repository.StudentRepository;
import com.leadConsult.interview.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
  private final StudentRepository studentRepository;
  private final StudentMapper studentMapper;

  @Autowired
  public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
    this.studentRepository = studentRepository;
    this.studentMapper = studentMapper;
  }

  @Override
  public List<StudentResponse> getAllStudents() {
    List<Student> responses = studentRepository.findAll();
    return studentMapper.listStudentToListStudentResponse(responses);
  }

  @Override
  public StudentResponse postStudent(StudentRequest studentRequest) {
    Student student = studentMapper.studentRequestToStudent(studentRequest);
    studentRepository.save(student);

    return studentMapper.studentToStudentResponse(student);
  }
}
