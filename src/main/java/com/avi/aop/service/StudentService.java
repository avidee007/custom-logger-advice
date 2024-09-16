package com.avi.aop.service;

import com.avi.aop.domain.Student;

public interface StudentService {

  Student create(Student student);

  Student getStudent(long id);
}