package com.avi.aop.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "REQUEST_LOG")
@Getter
@Setter
@NoArgsConstructor
public class RequestLogEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String method;
  private String message;
  @Version
  private Long version;
  @CreatedDate
  private Instant receivedAt;

  @Override
  public String toString() {
    return "RequestLogEntity{" +
        "id=" + id +
        ", method='" + method + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}