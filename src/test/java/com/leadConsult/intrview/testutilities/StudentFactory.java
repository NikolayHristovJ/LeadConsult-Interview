package com.leadConsult.intrview.testutilities;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;

import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_AGE;
import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_ID;
import static com.leadConsult.intrview.testutilities.StudentConstants.STUDENT_NAME;

public class StudentFactory {
  public static Student getDefaultStudent() {
    return new Student(STUDENT_ID,
                       STUDENT_NAME,
                       STUDENT_AGE,
                       null,
                       null
    );
  }

  public static StudentRequest getDefaultStudentRequest() {
    return new StudentRequest(STUDENT_NAME, STUDENT_AGE);
  }

  public static StudentResponse getDefaultStudentResponse() {
    return new StudentResponse(STUDENT_ID,
                       STUDENT_NAME,
                       STUDENT_AGE,
                       null
    );
  }
}
