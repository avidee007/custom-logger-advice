package com.avi.aop.aspect;

import com.avi.aop.annotations.LoggerAdvice;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AsyncDbLogService<T> {
  private final EntityManager entityManager;


  @Async
  @LoggerAdvice
  @Transactional
  public void logToDb(T entity) {
    entityManager.persist(entity);
  }


}