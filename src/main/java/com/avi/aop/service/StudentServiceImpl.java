package com.avi.aop.service;

import com.avi.aop.annotations.LoggerAdvice;
import com.avi.aop.domain.Student;
import com.avi.aop.jpa.assembler.StudentEntityAssembler;
import com.avi.aop.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
  private final StudentRepository repository;
  private final StudentEntityAssembler assembler;

  @Override
  @LoggerAdvice(logToDb = true)
  public Student create(Student student) {
    return assembler.fromEntity(repository.save(assembler.toEntity(student)));
  }

  @Override
  @LoggerAdvice
  public Student getStudent(long id) {
    return repository.findById(id)
        .map(assembler::fromEntity)
        .orElseThrow();
  }
}