package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.TeacherRequest;
import com.leadConsult.interview.dto.response.TeacherResponse;
import com.leadConsult.interview.entity.Course;
import com.leadConsult.interview.entity.Group;
import com.leadConsult.interview.entity.Teacher;
import com.leadConsult.interview.mapper.TeacherMapper;
import com.leadConsult.interview.repository.TeacherRepository;
import com.leadConsult.interview.service.CourseService;
import com.leadConsult.interview.service.GroupService;
import com.leadConsult.interview.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

  private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
  private final TeacherRepository teacherRepository;
  private final TeacherMapper teacherMapper;
  private final CourseService courseService;
  private final GroupService groupService;


  @Autowired
  public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper,
                            CourseService courseService, GroupService groupService) {
    this.teacherRepository = teacherRepository;
    this.teacherMapper = teacherMapper;
    this.courseService = courseService;
    this.groupService = groupService;
  }

  @Override
  public Teacher getTeacherFromRepository(Long teacherId) {
    return teacherRepository.findById(teacherId)
                           .orElseThrow(
                             () -> new EntityNotFoundException(String.format("Teacher not found with id: %s", teacherId)));
  }

  @Override
  public List<TeacherResponse> getAllTeachers() {
    log.info("Fetching all teachers");
    List<Teacher> teachers = teacherRepository.findAll();
    return teacherMapper.listTeacherToListTeacherResponse(teachers);
  }

  @Override
  public TeacherResponse postTeacher(TeacherRequest request){
    log.info("Saving teacher to the DB");
    Teacher teacher = teacherMapper.teacherRequestToTeacher(request);
    teacherRepository.save(teacher);

    return teacherMapper.teacherToTeacherResponse(teacher);
  }

  @Override
  public TeacherResponse getTeacherById(Long teacherId) {
    log.info(String.format("Fetching teacher with id: %s",teacherId));
    Teacher teacher = getTeacherFromRepository(teacherId);

    return teacherMapper.teacherToTeacherResponse(teacher);
  }

  @Override
  @Transactional
  public TeacherResponse editTeacher(Long teacherId, TeacherRequest request) {
    log.info(String.format("Editing teacher with teacher with id: %s",teacherId));
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
    log.info(String.format("Adding a teacher (id = %s) to a course (id = %s)",teacherId,courseId));
    Teacher teacher = getTeacherFromRepository(teacherId);
    Course course = courseService.getCourseFromRepository(courseId);

    teacher.addCourseToTeacher(course);
    teacherRepository.save(teacher);
  }

  @Override
  @Transactional
  public TeacherResponse deleteTeacher(Long teacherId) {
    log.info(String.format("Deleting teacher with id: %s",teacherId));
    Teacher teacher = getTeacherFromRepository(teacherId);
    teacherRepository.deleteById(teacher.getTeacherId());
    return teacherMapper.teacherToTeacherResponse(teacher);
  }

  @Override
  @Transactional
  public void addTeacherToGroup(Long teacherId, Long groupId) {
    log.info(String.format("Adding teacher (id = %s) to group (id = %s)",teacherId,groupId));
    Teacher teacher = getTeacherFromRepository(teacherId);
    Group group = groupService.getGroupFromRepository(groupId);

    teacher.setGroup(group);
    teacherRepository.save(teacher);
  }

  @Override
  public List<TeacherResponse> getTeacherByCourseAndGroup(Long courseId, Long groupId) {
    log.info(String.format("Fetching teachers with group (id = %s) and course (id = %s)",groupId,courseId));
    List<Teacher> teachers = teacherRepository.findByCourseIdAndGroupId(courseId, groupId);
    return teacherMapper.listTeacherToListTeacherResponse(teachers);
  }
}
