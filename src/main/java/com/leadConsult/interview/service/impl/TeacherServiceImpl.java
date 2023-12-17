package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.entity.Course;
import com.leadConsult.interview.entity.Teacher;
import com.leadConsult.interview.mapper.TeacherMapper;
import com.leadConsult.interview.repository.TeacherRepository;
import com.leadConsult.interview.service.CourseService;
import com.leadConsult.interview.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
  private final TeacherRepository teacherRepository;
  private final TeacherMapper teacherMapper;
  private final CourseService courseService;

  @Autowired
  public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper,
                            CourseService courseService) {
    this.teacherRepository = teacherRepository;
    this.teacherMapper = teacherMapper;
    this.courseService = courseService;
  }

  @Override
  public Teacher getTeacherFromRepository(Long teacherId) {
    return teacherRepository.findById(teacherId)
                           .orElseThrow(
                             () -> new EntityNotFoundException(String.format("Teacher not found with id:", teacherId)));
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

  @Override
  public TeacherResponse getTeacherById(Long teacherId) {
    Teacher teacher = getTeacherFromRepository(teacherId);

    return teacherMapper.teacherToTeacherResponse(teacher);
  }

  @Override
  @Transactional
  public TeacherResponse editTeacher(Long teacherId, TeacherRequest request) {
    Teacher oldTeacher = getTeacherFromRepository(teacherId);

    Teacher editedTeacher = teacherMapper.teacherRequestToTeacher(request);
    editedTeacher.setTeacherId(teacherId);
    editedTeacher.setTeachersCourses(oldTeacher.getTeachersCourses());
    TeacherResponse response = teacherMapper.teacherToTeacherResponse(oldTeacher);
    teacherRepository.save(editedTeacher);

    return response;
  }

  @Override
  @Transactional
  public void addCourseToTeacher(Long courseId, Long teacherId) {
    Teacher teacher = getTeacherFromRepository(teacherId);
    Course course = courseService.getCourseFromRepository(courseId);

    teacher.addCourseToTeacher(course);
    teacherRepository.save(teacher);
  }
}
