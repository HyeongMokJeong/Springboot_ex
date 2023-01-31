package com.example.projectname.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

@Aspect // AOP 클래스 선언: 부가 기능을 주입하는 클래스
@Component // Ioc 컨테이너가 해당 객체를 생성 및 관리
@Slf4j
public class DebuggingAspect extends com.example.projectname.aop.Aspect {

    // 어느 메소드를 타겟으로 할 것인지: api 패키지의 모든 메소드
    @Pointcut("execution(* com.example.projectname.api.*.*(..))")
    private void cut() {
    }

    // 실행 시점 설정 : cut()의 대상이 수행되기 이전에 수행
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) {
        // 입력값을 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = getClassName(joinPoint);

        // 메소드명
        String methodName = getMethodName(joinPoint);

        // 입력값 로깅하기
        for (Object obj : args) {
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }

    // 실행 시점 설정 : cut()에 지정된 메소드 호출 성공 후 실행
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint,
                                   Object returnObj) {

        // 클래스명
        String className = getClassName(joinPoint);

        // 메소드명
        String methodName = getMethodName(joinPoint);

        // 반환값 로깅
        log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
    }
}
