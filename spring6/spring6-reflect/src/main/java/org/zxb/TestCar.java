package org.zxb;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestCar {

    // 获取class对象的多种方式
    @Test
    public void test01() throws Exception {
        // 类名.claaa
        Class clazz1 = Car.class;
        // 对象.getClass()
        Class clazz2 = new Car().getClass();
        // Class.forName("类名的全路径")
        Class clazz3 = Class.forName("org.zxb.Car");

        System.out.println(clazz3);
        System.out.println(clazz2);
        System.out.println(clazz1);

        // 实例化
//        Car car = (Car) clazz3.getDeclaredConstructor().newInstance();
//
//        System.out.println(car);
    }

    // 获取构造方法
    @Test
    public void test02() throws Exception{
        Class clazz = Car.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println(constructor.getName() + constructor.getParameterCount());
        }

//        Constructor constructor = clazz.getConstructor(String.class, int.class, String.class);
//        Car car = (Car) constructor.newInstance("比亚迪", 20 , "蓝色");
//        System.out.println(car);
        // 如果构造方法是私有方法，那么如何创建实例。
        Constructor constructor1 = clazz.getDeclaredConstructor(String.class, int.class, String.class);
        constructor1.setAccessible(true);
        Car car1 = (Car) constructor1.newInstance("仰望", 1, "迷彩色");
        System.out.println(car1);
    }

    // 获取属性
    @Test
    public void test03() throws Exception{
        Class clazz = Car.class;
        // 得到所有的公开属性
//        clazz.getFields();
        // 得到所有的属性
        Car car = (Car) clazz.getDeclaredConstructor().newInstance();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
//            System.out.println(field.getName());
            if(field.getName().equals("name")){
                field.setAccessible(true);
                field.set(car, "蔚来");
            }
        }

        System.out.println(car);

    }
    // 获取方法
    @Test
    public void test04() throws Exception{
        Car car = new Car("别克", 2, "白色");
        Class clazz = car.getClass();
        // public方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            if (method.getName().equals("toString")){
                String invoke = (String) method.invoke(car);
                System.out.println(invoke);
            }
        }
    }
}
