package com.leadConsult.interview.mapper;

import com.leadConsult.interview.dto.request.StudentRequest;
import com.leadConsult.interview.dto.response.StudentResponse;
import com.leadConsult.interview.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
  Student studentRequestToStudent(StudentRequest request);
  StudentResponse studentToStudentResponse (Student student);
  List<StudentResponse> listStudentToListStudentResponse (List<Student> student);
}
