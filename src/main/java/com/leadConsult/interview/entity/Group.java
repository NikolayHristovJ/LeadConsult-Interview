package com.leadConsult.interview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Group {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "group_id")
  private Long groupId;

  @Column(name = "group_name")
  private String groupName;

  @OneToMany(mappedBy = "group")
  private Set<Student> students;

  @OneToMany(mappedBy = "group")
  private Set<Teacher> teachers;
}
