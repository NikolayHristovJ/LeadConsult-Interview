package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;

import java.util.List;

public interface StudentService {

  /**
   * Fetches Student with a specific Id if student is not found
   * an EntityNotFoundException will be thrown
   *
   * @param studentId
   * @return Student entity from the DB
   */
  Student getStudentFromRepository(Long studentId);

  /**
   * Fetches all students from the DB
   *
   * @return list of Student Responses
   */
  List<StudentResponse> getAllStudents();

  /**
   * Saves a student to the DB and returns the saved student as a response class
   *
   * @param studentRequest
   * @return StudentResponse
   */
  StudentResponse postStudent(StudentRequest studentRequest);

  /**
   *
   * @param studentId
   * @return StudentResponse
   */
  StudentResponse getStudentById(Long studentId);

  /**
   * Edits a student with the given information from the request class.
   * If student is not found by id this method will throw a EntityNotFoundException
   *
   * @param studentId
   * @param request
   * @return StudentResponse
   */
  StudentResponse editStudent(Long studentId, StudentRequest request);

  /**
   * Adds a student to a specific course
   *
   * @param courseId
   * @param studentId
   */
  void addCourseToStudent(Long courseId, Long studentId);

  /**
   * Adds a student to a specific group
   *
   * @param groupId
   * @param studentId
   */
  void addStudentToGroup(Long studentId, Long groupId);

  /**
   * Deletes a student based on the id.
   * If student is not found by id this method will throw a EntityNotFoundException
   *
   * @param studentId
   * @return StudentResponse
   */
  StudentResponse deleteStudent(Long studentId);

  /**
   * Fetches all students that are in a specific course and are above the given age
   *
   * @param courseId
   * @param age
   * @return List of Student Responses
   */
  List<StudentResponse> getAllStudentsInCourseAndAboveAge(Long courseId, int age);

  /**
   * Fetches all students that in a specific group
   *
   * @param groupId
   * @return List of Student Responses
   */
  List<StudentResponse> getStudentByGroup(Long groupId);

  /**
   * Fetches all students that are in a specific group and course
   *
   * @param courseId
   * @param groupId
   * @return List of Student Responses
   */
  List<StudentResponse> getStudentByCourseAndGroup(Long courseId, Long groupId);

  /**
   * Fetches all students that are in a specific course
   *
   * @param courseId
   * @return list of students
   */
  List<StudentResponse> getAllStudentsInCourse(Long courseId);
}
