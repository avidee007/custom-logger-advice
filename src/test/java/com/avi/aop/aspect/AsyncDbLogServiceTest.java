package com.avi.aop.aspect;

import com.avi.aop.jpa.entity.RequestLogEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AsyncDbLogServiceTest {
  @Mock
  private EntityManager manager;

  @InjectMocks
  private AsyncDbLogService<RequestLogEntity> asyncDbLogService;

  @Test
  void test_logToDb() {

    Mockito.doNothing().when(manager).persist(Mockito.any());

    Assertions.assertDoesNotThrow(() -> asyncDbLogService.logToDb(new RequestLogEntity()));
  }
}