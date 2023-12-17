package com.leadConsult.interview.mapper;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.entity.Teacher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
  List<TeacherResponse> listTeacherToListTeacherResponse(List<Teacher> teachers);
  Teacher teacherRequestToTeacher(TeacherRequest teacherRequest);
  TeacherResponse teacherToTeacherResponse (Teacher teacher);
}
