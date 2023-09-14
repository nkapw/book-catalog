package com.tentang.tech.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LoggingAspect {

  public void beforeExecuteLogging() {
    log.info("this is log from aspect");
  }
}
