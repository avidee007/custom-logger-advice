package com.avi.aop.jpa.repository;

import com.avi.aop.jpa.entity.RequestLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLogEntity, Long> {
}