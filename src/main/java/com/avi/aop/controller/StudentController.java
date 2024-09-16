package com.avi.aop.controller;

import com.avi.aop.domain.Student;
import com.avi.aop.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @PostMapping
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    return ResponseEntity.ok(studentService.create(student));
  }

  @GetMapping("{id}")
  public ResponseEntity<Student> getStudent(@PathVariable("id") long id) {
    return ResponseEntity.ok(studentService.getStudent(id));
  }

}