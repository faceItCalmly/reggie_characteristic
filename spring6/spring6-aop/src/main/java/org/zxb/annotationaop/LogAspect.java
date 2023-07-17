package org.zxb.annotationaop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// 切面类
@Component // 表示创建这个类的实例交给spring进行管理
@Aspect // 表示这是一个切面类
public class LogAspect {
    // 设置切入点和通知类型
    /**
     * 通知类型：
     * 前置@Before(value="切入点表达式配置切入点")
     * 返回@AfterReturning （返回通知可以得到返回值）
     * 异常@AfterThrowing  （目标方法方法出现了异常，这个通知会执行）
     * 后置@After()    
     * 环绕@Around()
     */

    @Before(value = "execution(public int org.zxb.annotationaop.CalculatorImpl.add(..))")
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println("前置通知-已经执行.....");
    }

    @After(value="execution(public int org.zxb.annotationaop.CalculatorImpl.add(..))")
    public void afterMethod(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println("后置通知-已经执行..." + name);
    }

    @AfterReturning(value="execution(public int org.zxb.annotationaop.CalculatorImpl.add(..))", returning = "result")
    public void afterReturnMethod(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println("返回通知-已经执行..." + name);
        System.out.println("返回结果：" + result);
    }

//    @Around(value="execution(public int org.zxb.annotationaop.CalculatorImpl.add(..))")
//    public void aroundMethod(JoinPoint joinPoint){
//        String name = joinPoint.getSignature().getName();
//        System.out.println("环绕通知-已经执行..." + name);
//    }

}
