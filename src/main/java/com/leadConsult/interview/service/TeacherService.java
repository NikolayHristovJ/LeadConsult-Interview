package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;

import java.util.List;

public interface TeacherService {

  List<TeacherResponse> getAllTeachers();

  TeacherResponse postTeacher(TeacherRequest request);
}
