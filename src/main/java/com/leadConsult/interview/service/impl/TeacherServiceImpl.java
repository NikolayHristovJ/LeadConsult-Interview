package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.entity.Teacher;
import com.leadConsult.interview.mapper.TeacherMapper;
import com.leadConsult.interview.repository.TeacherRepository;
import com.leadConsult.interview.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
  private final TeacherRepository teacherRepository;
  private final TeacherMapper teacherMapper;

  @Autowired
  public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
    this.teacherRepository = teacherRepository;
    this.teacherMapper = teacherMapper;
  }

  @Override
  public List<TeacherResponse> getAllTeachers() {
    List<Teacher> teachers = teacherRepository.findAll();
    return teacherMapper.listTeacherToListTeacherResponse(teachers);
  }

  @Override
  public TeacherResponse postTeacher(TeacherRequest request){
    Teacher teacher = teacherMapper.teacherRequestToTeacher(request);
    teacherRepository.save(teacher);

    return teacherMapper.teacherToTeacherResponse(teacher);
  }
}
