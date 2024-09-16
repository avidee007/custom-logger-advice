package com.avi.aop.jpa.repository;

import com.avi.aop.jpa.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}