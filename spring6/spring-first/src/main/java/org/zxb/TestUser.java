package org.zxb;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;

public class TestUser {

    private Logger logger = LoggerFactory.getLogger(TestUser.class);
    @Test
    public void TestUserObject(){
        // 加载Spring配置文件，进行对象的创建
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 获取创建的对象
        User user = (User) context.getBean("user");
        // 使用对象调用方法进行测试
        user.add();

        logger.info("调用成功！");

    }

    // 反射创建对象
    @Test
    public void TestUserObject1() throws Exception {
        Class clazz = Class.forName("org.zxb.User");
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        user.add();
    }
}
