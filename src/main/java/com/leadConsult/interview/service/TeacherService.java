package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.entity.Teacher;

import java.util.List;

public interface TeacherService {

  /**
   * Fetches a teacher with the specified id
   *
   * @param teacherId
   * @return TeacherResponse
   */
  Teacher getTeacherFromRepository(Long teacherId);

  /**
   * Fetches all teachers
   *
   * @return list of Teacher Responses
   */
  List<TeacherResponse> getAllTeachers();

  /**
   * Saves a teacher to the DB and returns the saved teacher as a response class
   *
   * @param request
   * @return TeacherResponse
   */
  TeacherResponse postTeacher(TeacherRequest request);

  /**
   * Fetches teacher by id. If the teacher is not found an EntityNotFoundException will be thrown
   *
   * @param teacherId
   * @return TeacherResponse
   */
  TeacherResponse getTeacherById(Long teacherId);

  /**
   * Edits a specific teacher with the request class given and returns the old version of the teacher.
   * If the teacher is not found an EntityNotFoundException will be thrown
   *
   * @param teacherId
   * @param request
   * @return TeacherResponse
   */
  TeacherResponse editTeacher(Long teacherId, TeacherRequest request);

  /**
   * Adds a teacher to a specific course. If the course or the teacher is not found an EntityNotFoundException will be thrown
   *
   * @param courseId
   * @param teacherId
   */
  void addCourseToTeacher(Long courseId, Long teacherId);

  /**
   * Deletes a teacher based on the given id. If the teacher is not found an EntityNotFoundException will be thrown
   *
   * @param teacherId
   * @return TeacherResponse
   */
  TeacherResponse deleteTeacher(Long teacherId);

  /**
   * Adds a teacher to a group based on the ids given. If the teacher or the group is not found an EntityNotFoundException will be thrown
   *
   * @param teacherId
   * @param groupId
   */
  void addTeacherToGroup(Long teacherId, Long groupId);

  /**
   * Fetches all teachers by course and by group ids.
   *
   * @param courseId
   * @param groupId
   * @return list of Teacher Responses
   */
  List<TeacherResponse> getTeacherByCourseAndGroup(Long courseId, Long groupId);
}
