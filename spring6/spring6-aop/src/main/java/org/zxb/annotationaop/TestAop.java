package org.zxb.annotationaop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Calculator calculator = (Calculator)context.getBean(Calculator.class);
        calculator.add(1,2);
    }
}
