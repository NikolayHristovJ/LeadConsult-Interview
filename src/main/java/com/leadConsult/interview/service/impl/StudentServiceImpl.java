package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;
import com.leadConsult.interview.mapper.StudentMapper;
import com.leadConsult.interview.repository.StudentRepository;
import com.leadConsult.interview.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
  public Student getStudentFromRepository(Long studentId) {
    return studentRepository.findById(studentId)
                           .orElseThrow(
                             () -> new EntityNotFoundException(String.format("Student not found with id:", studentId)));
  }

  @Override
  public List<StudentResponse> getAllStudents() {
    List<Student> students = studentRepository.findAll();
    return studentMapper.listStudentToListStudentResponse(students);
  }

  @Override
  public StudentResponse postStudent(StudentRequest studentRequest) {
    Student student = studentMapper.studentRequestToStudent(studentRequest);
    studentRepository.save(student);

    return studentMapper.studentToStudentResponse(student);
  }

  @Override
  public StudentResponse getStudentById(Long studentId) {
    Student student = getStudentFromRepository(studentId);
    return studentMapper.studentToStudentResponse(student);
  }

  @Override
  @Transactional
  public StudentResponse editStudent(Long studentId, StudentRequest request) {
    Student oldStudent = getStudentFromRepository(studentId);

    Student editedStudent = studentMapper.studentRequestToStudent(request);
    editedStudent.setStudentId(studentId);
    editedStudent.setStudentsCourses(oldStudent.getStudentsCourses());
    StudentResponse response = studentMapper.studentToStudentResponse(oldStudent);
    studentRepository.save(editedStudent);

    return response;
  }
}
