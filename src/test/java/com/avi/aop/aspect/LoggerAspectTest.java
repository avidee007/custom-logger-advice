package com.avi.aop.aspect;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.avi.aop.annotations.LoggerAdvice;
import com.avi.aop.jpa.entity.RequestLogEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoggerAspectTest {

  @Mock
  private AsyncDbLogService<RequestLogEntity> asyncDbLogService;

  @Mock
  private ProceedingJoinPoint joinPoint;
  @Mock
  private Signature signature;

  @InjectMocks
  private LoggerAspect loggerAspect;

  @Test
  void test_loggerAdvice_with_logToDb_true() throws Throwable {

    Object[] args = new Object[]{1};
    Mockito.when(joinPoint.getArgs()).thenReturn(args);
    Mockito.when(joinPoint.getSignature()).thenReturn(signature);

    Mockito.when(signature.getName()).thenReturn("testMethodWithDbLogging");


    LoggerAdvice loggerAdvice = Mockito.mock(LoggerAdvice.class);
    Mockito.when(loggerAdvice.logToDb()).thenReturn(true);

    Mockito.doNothing().when(asyncDbLogService).logToDb(Mockito.any(RequestLogEntity.class));


    Assertions.assertDoesNotThrow(()->loggerAspect.loggerAdvice(joinPoint, loggerAdvice));

    verify(asyncDbLogService, times(1)).logToDb(Mockito.any(RequestLogEntity.class));


  }

  @Test
  void test_loggerAdvice_with_logToDb_false() throws Throwable {

    Object[] args = new Object[]{1};
    Mockito.when(joinPoint.getArgs()).thenReturn(args);
    Mockito.when(joinPoint.getSignature()).thenReturn(signature);
    Mockito.when(signature.getName()).thenReturn("testMethodWithoutDbLogging");

    LoggerAdvice loggerAdvice = Mockito.mock(LoggerAdvice.class);
    Mockito.when(loggerAdvice.logToDb()).thenReturn(false);

    Assertions.assertDoesNotThrow(()->loggerAspect.loggerAdvice(joinPoint, loggerAdvice));

    verifyNoInteractions(asyncDbLogService);


  }
}