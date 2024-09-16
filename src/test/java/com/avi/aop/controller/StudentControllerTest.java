package com.avi.aop.controller;

import com.avi.aop.domain.Student;
import com.avi.aop.service.StudentService;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
  @Mock
  private StudentService studentService;
  @InjectMocks
  private StudentController controller;

  @Test
  void test_createStudent() {
    var student = new Student(null, "test", "test");
    var responseStudent = new Student(1L, "test", "test");

    Mockito.when(studentService.create(student)).thenReturn(responseStudent);

    ResponseEntity<Student> studentResponseEntity = controller.createStudent(student);

    Assertions.assertEquals(HttpStatus.OK,studentResponseEntity.getStatusCode());
    Assertions.assertEquals(1L, Objects.requireNonNull(studentResponseEntity.getBody()).id());
    Assertions.assertEquals(responseStudent.firstName(), Objects.requireNonNull(studentResponseEntity.getBody()).firstName());

  }

  @Test
  void test_getStudent() {
    var responseStudent = new Student(1L, "test", "test");

    Mockito.when(studentService.getStudent(1L)).thenReturn(responseStudent);

    ResponseEntity<Student> studentResponseEntity = controller.getStudent(1L);

    Assertions.assertEquals(HttpStatus.OK,studentResponseEntity.getStatusCode());
    Assertions.assertEquals(1L, Objects.requireNonNull(studentResponseEntity.getBody()).id());
    Assertions.assertEquals(responseStudent.firstName(), Objects.requireNonNull(studentResponseEntity.getBody()).firstName());
  }
}