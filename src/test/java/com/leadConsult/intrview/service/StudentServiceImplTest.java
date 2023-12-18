package com.leadConsult.intrview.service;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Group;
import com.leadConsult.interview.entity.Student;
import com.leadConsult.interview.mapper.StudentMapper;
import com.leadConsult.interview.repository.StudentRepository;
import com.leadConsult.interview.service.GroupService;
import com.leadConsult.interview.service.impl.StudentServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.leadConsult.intrview.testutilities.GroupConstants.GROUP_ID;
import static com.leadConsult.intrview.testutilities.GroupFactory.getDefaultGroup;
import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_ID;
import static com.leadConsult.intrview.testutilities.StudentFactory.getDefaultStudent;
import static com.leadConsult.intrview.testutilities.StudentFactory.getDefaultStudentRequest;
import static com.leadConsult.intrview.testutilities.StudentFactory.getDefaultStudentResponse;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

  private StudentResponse studentResponse;
  private StudentRequest studentRequest;
  private Student student;
  private Group group;

  @Mock
  private StudentRepository studentRepository;

  @Mock
  private GroupService groupService;

  @Mock
  private StudentMapper studentMapper;

  @InjectMocks
  private StudentServiceImpl studentService;

  @Before
  public void setup() {
    studentRequest = getDefaultStudentRequest();
    student = getDefaultStudent();
    studentResponse = getDefaultStudentResponse();
    group = getDefaultGroup();
  }

  @Test
  public void getStudentById_doesItFindStudent_success() {
    when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.ofNullable(student));
    when(studentMapper.studentToStudentResponse(student)).thenReturn(studentResponse);

    StudentResponse actual = studentService.getStudentById(STUDENT_ID);

    assertEquals(studentResponse.getStudentId(), actual.getStudentId());
    assertEquals(studentResponse.getStudentName(), actual.getStudentName());
    assertEquals(studentResponse.getAge(), actual.getAge());
    assertEquals(studentResponse.getGroup(), actual.getGroup());
  }

  @Test(expected = EntityNotFoundException.class)
  public void getStudentById_studentNotFoundException_fail() {
    when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.empty());

    studentService.getStudentById(STUDENT_ID);
  }

  @Test
  public void addNewStudent_doesItAddStudent_success() {
    when(studentMapper.studentRequestToStudent(studentRequest)).thenReturn(student);
    when(studentMapper.studentToStudentResponse(student)).thenReturn(studentResponse);

    StudentResponse actual = studentService.postStudent(studentRequest);

    assertEquals(studentResponse.getStudentId(), actual.getStudentId());
    assertEquals(studentResponse.getStudentName(), actual.getStudentName());
    assertEquals(studentResponse.getAge(), actual.getAge());
    assertEquals(studentResponse.getGroup(), actual.getGroup());

    verify(studentRepository).save(any());
  }

  @Test
  public void deleteStudent_success(){
    when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.ofNullable(student));
    when(studentMapper.studentToStudentResponse(student)).thenReturn(studentResponse);

    StudentResponse actual = studentService.deleteStudent(STUDENT_ID);

    assertEquals(studentResponse.getStudentId(), actual.getStudentId());
    assertEquals(studentResponse.getStudentName(), actual.getStudentName());
    assertEquals(studentResponse.getAge(), actual.getAge());
    assertEquals(studentResponse.getGroup(), actual.getGroup());

    verify(studentRepository).findById(STUDENT_ID);
    verify(studentRepository).deleteById(STUDENT_ID);
  }

  @Test(expected = EntityNotFoundException.class)
  public void deleteStudent_studentNotFoundException_fail() {
    when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.empty());

    studentService.deleteStudent(STUDENT_ID);
  }

  @Test
  public void addStudentToGroup_success(){
    when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.ofNullable(student));
    when(groupService.getGroupFromRepository(GROUP_ID)).thenReturn(group);

    studentService.addStudentToGroup(STUDENT_ID,GROUP_ID);

    verify(studentRepository).findById(STUDENT_ID);
    verify(groupService).getGroupFromRepository(GROUP_ID);
    verify(studentRepository).save(student);
  }
}
