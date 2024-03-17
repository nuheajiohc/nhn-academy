package com.nhnacademy.edu.springframework.aop;

import com.nhnacademy.edu.springframework.messagesender.MessageSender;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class TimeAspect {

    @Around("@annotation(com.nhnacademy.edu.springframework.annotation.TargetMethod) && target(messageSender)")
    public Object logging(ProceedingJoinPoint joinPoint, MessageSender messageSender) throws Throwable {
        String className = messageSender.getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        StopWatch stopWatch = new StopWatch(className + "." + methodName);

        try {
            stopWatch.start();
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            System.out.printf("[%s].[%s] [%s]ms", className, methodName, stopWatch.getTotalTimeMillis());
        }
    }
}