package com.avi.aop.aspect;

import com.avi.aop.annotations.LoggerAdvice;
import com.avi.aop.jpa.entity.RequestLogEntity;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggerAspect {

  private final AsyncDbLogService<RequestLogEntity> asyncDbLogService;

  @Around("@annotation(loggerAdvice)")
  public Object loggerAdvice(ProceedingJoinPoint joinPoint, LoggerAdvice loggerAdvice)
      throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    String message =
        String.format("method: [%s] invoked at %s with arguments: %s.", methodName, Instant.now(),
            getParams(joinPoint));
    log.info(message);
    if (loggerAdvice.logToDb()) {
      asyncDbLogService.logToDb(getEntity(methodName, message));
    }
    return joinPoint.proceed();
  }

  private String getParams(ProceedingJoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    return Arrays.stream(args)
        .map(Object::toString)
        .collect(Collectors.joining(","));
  }

  private RequestLogEntity getEntity(String methodName, String message) {
    RequestLogEntity entity = new RequestLogEntity();
    entity.setMethod(methodName);
    entity.setMessage(message);
    entity.setReceivedAt(Instant.now());
    return entity;
  }


}