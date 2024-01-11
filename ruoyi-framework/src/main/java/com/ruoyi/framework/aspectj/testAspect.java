package com.ruoyi.framework.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class testAspect {


    @Before("execution(* com.ruoyi.system.service.impl.lptest.*())")
    public void dodo()
    {
    System.out.println("iam Before Aspect");

    }

      @After("execution(* com.ruoyi.system.service.impl.lptest.*())")
    public void dodo2()
    {
    System.out.println("iam After Aspect");

    }


    // public void String do1()
    // {
    //     System.out.println("iam test Aspect");
    //     return null;
       
    // }
    
}
