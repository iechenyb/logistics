package com.genpact.logistics.modules.order.aspect;

//import org.aopalliance.intercept.MethodInterceptor;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class OrderAspect  {
	
	@AfterThrowing(pointcut="execution(* com.genpact.logistics.modules.order.controller.OrderController.*(..))",throwing="e")
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
	/*@Around ("execution(* com.genpact.logistics.modules.order.controller.OrderController.*(..))")
	public Object aroudMethod(ProceedingJoinPoint point) throws Throwable{
		System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
            //args[0] = "改变后的参数1";
        	System.out.println("参数："+args[0]);
        }
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return "原返回值：" + returnValue + "，这是返回结果的后缀";
	}*/
	
	@Before("execution(* com.genpact.logistics.modules.order.controller.OrderController.*(..))")
	    public void permissionCheck(JoinPoint point) {	
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String str = request.getRequestURL()+","+request.getRequestURI();
			 Object[] args = point.getArgs();
		       for(Object obj:args) {
		            //args[0] = "改变后的参数1";
		        	System.out.println("参数："+obj);
		        }	
			System.out.println("@Before：模拟权限检查..."+str);
	        System.out.println("@Before：目标方法为：" + 
	                point.getSignature().getDeclaringTypeName() + 
	                "." + point.getSignature().getName());
	        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
	        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
	        //抛出指定异常或者重定向页面
	}
	    
	  @AfterReturning(pointcut="execution(* com.genpact.logistics.modules.order.controller.OrderController.*(..))", 
	        returning="returnValue")
	    public void log(JoinPoint point, Object returnValue) {
	        System.out.println("@AfterReturning：模拟日志记录功能...");
	        System.out.println("@AfterReturning：目标方法为：" + 
	                point.getSignature().getDeclaringTypeName() + 
	                "." + point.getSignature().getName());
	        System.out.println("@AfterReturning：参数为：" + 
	                Arrays.toString(point.getArgs()));
	        System.out.println("@AfterReturning：返回值为：" + returnValue);
	        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());       
	    }
	    
	    @After("execution(* com.genpact.logistics.modules.order.controller.OrderController.*(..))")
	    public void releaseResource(JoinPoint point) {
	        System.out.println("@After：模拟释放资源...");
	        System.out.println("@After：目标方法为：" + 
	                point.getSignature().getDeclaringTypeName() + 
	                "." + point.getSignature().getName());
	        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
	        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
	    }
	
}
