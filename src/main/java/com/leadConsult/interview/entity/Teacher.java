package com.leadConsult.interview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "teacher_id")
  private Long teacherId;

  @Column(name = "teacher_name")
  private String teacherName;

  private Integer age;

  @ManyToMany
  @JoinTable(
    name = "teachers_courses",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private Set<Course> teachersCourses;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private Group group;

  public void addCourseToTeacher(Course course){
    this.teachersCourses.add(course);
  }

  public Long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Long teacherId) {
    this.teacherId = teacherId;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Set<Course> getTeachersCourses() {
    return teachersCourses;
  }

  public void setTeachersCourses(Set<Course> teachersCourses) {
    this.teachersCourses = teachersCourses;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }
}
