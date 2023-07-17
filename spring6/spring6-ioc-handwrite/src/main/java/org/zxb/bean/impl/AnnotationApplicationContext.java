package org.zxb.bean.impl;

import org.zxb.bean.ApplicationContext;

import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext {

    // 用一个Map来存放bean对象
    private Map<Class, Object> beanFactory = new HashMap<>();

    // 返回对象
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    // 创建有参构造，传递包路径，设置包扫描规则
    // 当前包及其子包，有@bean注解的就通过反射进行实例化。
    public AnnotationApplicationContext(String basePackage) throws Exception{
        // org.zxb
        // 把 . 替换成 \
        basePackage.replaceAll("\\.", "\\\\");

        // 获取包的绝对路径

        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(basePackage);
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            URLDecoder.decode(url.getFile(), "utf-8");
            
        }
    }

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationApplicationContext("org.zxb");
//        context.getBean();
    }

}
