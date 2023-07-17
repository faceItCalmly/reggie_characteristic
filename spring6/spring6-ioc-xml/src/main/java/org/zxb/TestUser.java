package org.zxb;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    @Test
    void TestUserObject(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        User user = (User) context.getBean("user");
        System.out.println("根据id获取bean对象");
        user.run();
        // 根据类型获取对象
        User user1 = (User) context.getBean(User.class);
        System.out.println("根据类型获取bean对象");
        user1.run();

        // 根据 id 和 类型 获取bean对象
        User user2 = (User) context.getBean("user", User.class);
        System.out.println("根据id和类型获取bean对象");
        user2.run();

    }

    @Test
    void TestBookObject(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Book book = context.getBean("book1", Book.class);
        System.out.println(book);
        Book book2 = context.getBean("book2",Book.class);
        System.out.println(book2);
    }


}
