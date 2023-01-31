package com.example.projectname.aop;

import org.aspectj.lang.JoinPoint;

public class Aspect {
    String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget()
                .getClass()
                .getSimpleName();
    }

    String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature()
                .getName();
    }
}
