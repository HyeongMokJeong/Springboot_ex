package com.example.projectname.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD}) // 어노테이션 적용 대상
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 유지 기간을 런타임 시점까지
public @interface RuningTime {
}
