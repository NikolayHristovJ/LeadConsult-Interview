package com.leadConsult.intrview.handler;

import com.leadConsult.interview.controller.StudentController;
import com.leadConsult.interview.handler.GlobalExceptionHandler;
import com.leadConsult.interview.service.impl.StudentServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_ID;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

  private MockMvc mockMvc;

  @Mock
  private StudentServiceImpl studentService;

  @InjectMocks
  private StudentController studentController;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders
      .standaloneSetup(studentController)
      .setControllerAdvice(new GlobalExceptionHandler())
      .build();
  }

  @Test
  public void handleEntityNotFoundException_returnsNotFoundResponse() throws Exception {
    EntityNotFoundException exception = new EntityNotFoundException("exception message");

    when(studentService.getStudentById(STUDENT_ID))
      .thenThrow(exception);

    mockMvc.perform(get("/v1/api/students/{studentId}", STUDENT_ID)
                      .param("param", "value"))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("$.Errors[0]").exists());
  }

}
