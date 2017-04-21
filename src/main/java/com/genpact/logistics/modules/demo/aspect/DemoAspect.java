package com.genpact.logistics.modules.demo.aspect;

//import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DemoAspect  {
	
	@Pointcut("execution(* com.genpact.logistics.modules.demo.aspect.DemoAspectModel.*(..))")
	//@Pointcut("execution(* com.genpact.logistics.modules.order.controller.OrderController.*(..))")
	public void cutMethod(){
		
	}
	
	//切入到注解
//	@Pointcut("@annotation(javax.ws.rs.GET)")
//	public void cutAnnotation(){ }
	//合集切入
//	@Pointcut("cutMethod() && cutAnnotation()")
//	public void cutBoth(){ }
	
	@AfterThrowing(pointcut="cutMethod()",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,Exception e){
		System.out.println(joinPoint.getKind());
		System.out.println(joinPoint.getSignature().getDeclaringTypeName());
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("=======getMessage begin===========");
		System.out.println(e.getMessage());
		System.out.println("=======getMessage end===========");
		System.out.println("=======getCause begin===========");
		System.out.println(e.getCause());
		System.out.println("=======getCause end===========");
		System.out.println("=======getStackTrace begin===========");
		StackTraceElement[] stackTraces = e.getStackTrace();
		for (StackTraceElement o : stackTraces) {
			System.out.println(o.toString());
		}
		
		System.out.println("=======getStackTrace end===========");
        
    }
	@Around ("cutMethod()")
	public Object aroudMethod(ProceedingJoinPoint pjp) throws Throwable{
		Object[] args = pjp.getArgs();
		Object	obj = pjp.proceed();
		return obj;
	}
	
	@After(value="cutMethod()")
	public void afterAop(){
		System.out.println("aop after .......");
	}
	
}
