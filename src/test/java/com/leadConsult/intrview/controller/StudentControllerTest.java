package com.leadConsult.intrview.controller;

import com.leadConsult.interview.controller.StudentController;
import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;
import com.leadConsult.interview.service.impl.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_AGE;
import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_ID;
import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_NAME;
import static com.leadConsult.intrview.testutilities.StudentFactory.getDefaultStudent;
import static com.leadConsult.intrview.testutilities.StudentFactory.getDefaultStudentRequest;
import static com.leadConsult.intrview.testutilities.StudentFactory.getDefaultStudentResponse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

  private StudentResponse studentResponse;
  private StudentRequest studentRequest;
  private Student student;
  private MockMvc mockMvc;

  @Mock
  private StudentServiceImpl studentService;

  @InjectMocks
  private StudentController studentController;

  @Before
  public void setup() {
    studentRequest = getDefaultStudentRequest();
    student = getDefaultStudent();
    studentResponse = getDefaultStudentResponse();

    mockMvc = MockMvcBuilders
      .standaloneSetup(studentController)
      .build();
  }

  @Test
  public void getStudentById_doesItReturnStudent_success() throws Exception{
    when(studentService.getStudentById(STUDENT_ID)).thenReturn(studentResponse);

    mockMvc.perform(get("/v1/api/students/" + STUDENT_ID))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.studentId").value(STUDENT_ID))
           .andExpect(jsonPath("$.studentName").value(STUDENT_NAME))
           .andExpect(jsonPath("$.age").value(STUDENT_AGE));
  }

}
