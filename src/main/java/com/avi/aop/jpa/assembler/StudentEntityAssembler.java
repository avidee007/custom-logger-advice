package com.avi.aop.jpa.assembler;

import com.avi.aop.domain.Student;
import com.avi.aop.jpa.entity.StudentEntity;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class StudentEntityAssembler {

  public StudentEntity toEntity(Student student) {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setFirstName(student.firstName());
    studentEntity.setLastName(student.lastName());
    studentEntity.setCreated(Instant.now());
    studentEntity.setModified(Instant.now());
    return studentEntity;

  }

  public Student fromEntity(StudentEntity entity) {
    return new Student(entity.getId(), entity.getFirstName(), entity.getLastName());
  }

}