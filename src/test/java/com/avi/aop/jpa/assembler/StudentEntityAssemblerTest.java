package com.avi.aop.jpa.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.avi.aop.domain.Student;
import com.avi.aop.jpa.entity.StudentEntity;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentEntityAssemblerTest {
  private final StudentEntityAssembler assembler = new StudentEntityAssembler();

  @Test
  void test_toEntity() {
    var student = new Student(1L, "firstName", "lastName");

    StudentEntity entity = assembler.toEntity(student);

    assertEquals(entity.getFirstName(), student.firstName());
    assertEquals(entity.getLastName(), student.lastName());
  }

  @Test
  void test_fromEntity() {


    StudentEntity entity = getEntity();
    Student student = assembler.fromEntity(entity);


    assertEquals(student.id(), entity.getId());
    assertEquals(student.firstName(), entity.getFirstName());
    assertEquals(student.lastName(), entity.getLastName());


  }

  private StudentEntity getEntity() {
    StudentEntity entity = new StudentEntity();
    entity.setId(1L);
    entity.setFirstName("firstName");
    entity.setLastName("lastName");
    entity.setModified(Instant.now());
    entity.setCreated(Instant.now());
    return entity;
  }
}