package com.mz.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DonotEditAOP {
//    @Pointcut("execution(* com.mz.admin.controller.*.*add*(..))")
//    public void pointCutAdd(){}
//
//    @Pointcut("execution(* com.mz.admin.controller.*.*update*(..))")
//    public void pointCutUpdate(){}
//
//    @Pointcut("execution(* com.mz.admin.controller.*.*delete*(..))")
//    public void pointCutDelete(){}
//
//    @Pointcut("execution(* com.mz.admin.controller.*.*edit*(..))")
//    public void pointCutEdit(){}
//
//    @Pointcut("pointCutAdd() || pointCutUpdate() || pointCutEdit() || pointCutDelete()")
//    public void pointCutAll(){}
//
//    @Before("pointCutAll()")
//    public void donotEdit(){
//        throw new RuntimeException("演示模式，不允许修改");
//    }
}
