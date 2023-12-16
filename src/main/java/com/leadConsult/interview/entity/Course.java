package com.leadConsult.interview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "course_id")
  private Long courseId;

  @Column(name = "course_name")
  private String courseName;

  private String type;

  @ManyToMany(mappedBy = "studentsCourses")
  private Set<Student> students;

  @ManyToMany(mappedBy = "teachersCourses")
  private Set<Teacher> teachers;

}
