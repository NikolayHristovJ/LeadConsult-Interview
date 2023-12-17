package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.entity.Teacher;

import java.util.List;

public interface TeacherService {

  Teacher getTeacherFromRepository(Long teacherId);

  List<TeacherResponse> getAllTeachers();

  TeacherResponse postTeacher(TeacherRequest request);

  TeacherResponse getTeacherById(Long teacherId);

  TeacherResponse editTeacher(Long teacherId, TeacherRequest request);

  void addCourseToTeacher(Long courseId, Long teacherId);

  TeacherResponse deleteTeacher(Long teacherId);
}
