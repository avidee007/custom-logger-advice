package com.avi.aop.service;

import com.avi.aop.domain.Student;
import com.avi.aop.jpa.assembler.StudentEntityAssembler;
import com.avi.aop.jpa.entity.StudentEntity;
import com.avi.aop.jpa.repository.StudentRepository;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
  @Mock
  private StudentRepository repository;
  @Mock
  private StudentEntityAssembler assembler;

  @InjectMocks
  private StudentServiceImpl studentService;

  @Test
  void test_create() {
    var student = new Student(null, "firstName", "lastName");
    StudentEntity studentEntity = getStudentEntity();

    Mockito.when(assembler.toEntity(student)).thenReturn(studentEntity);
    Mockito.when(repository.save(Mockito.any(StudentEntity.class))).thenReturn(studentEntity);
    Mockito.when(assembler.fromEntity(studentEntity)).thenReturn(student);

    Student actualStudent = studentService.create(student);

    Assertions.assertEquals(1L, actualStudent.id());
    Assertions.assertEquals(student.firstName(), actualStudent.firstName());
    Assertions.assertEquals(student.lastName(), actualStudent.lastName());

  }

  @Test
  void test_getStudent() {

    var student = new Student(1L, "firstName", "lastName");
    StudentEntity entity = getStudentEntity();

    Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
    Mockito.when(assembler.fromEntity(entity)).thenReturn(student);

    Student actualStudent = studentService.getStudent(1L);

    Assertions.assertEquals(student, actualStudent);
  }

  @Test
  void test_getStudent_failure() {
    Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
    Assertions.assertThrows(NoSuchElementException.class, () -> studentService.getStudent(1L));

  }

  private StudentEntity getStudentEntity() {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setId(1L);
    studentEntity.setFirstName("firstName");
    studentEntity.setLastName("lastName");
    studentEntity.setModified(Instant.now());
    return studentEntity;
  }
}