# 这是跟着尚硅谷的spring课程学习的写的代码。

## 环境要求
JKD : java17+
Maven : 3.6+
Spring : 6.0.2

## maven 工程的结构
父工程 : spring6
    子模块1 : spring-first
    子模块2 : spring-second
    ...

## 入门级案例开发步骤
>1. 引入Spring相关依赖
>2. 创建类，定义属性和方法
>3. 按照spring要求创建配置文件（xml)格式
>4. 在Spring配置文件中配置相关信息
>5. 进行最终的测试
1. 引入相关依赖\
    注意引入了spring-context依赖之后就相当于将spring的基础依赖都引入了。
    ```
       <dependencies>
        <!--spring-context依赖-->
        <!--引入了spring-context依赖，就表示将spring的基础依赖引入了-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.0.7</version>
        </dependency>
        <!--junit进行测试-->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.9.2</version>
        </dependency>
    </dependencies>
   ```
2. 创建类
    创建一个类User，定义一个方法add()
3. 按照spring要求创建配置文件（xml)格式,以及\
    在resources目录下创建一个spring xml的配置文件，使用idea并且在已经引入spring的依赖之后通过右键新建就会找到一个springxml的文件
    如果没有就将maven刷新一下。文件名称可以随便定义，在这里使用了beans.xml
    ```
    <!-- 完成User对象的创建
        bean 标签
            id属性 : 唯一标识
            class属性 : 要创建对象所在类的全路径
    -->
    <bean id="user" class="org.zxb.User"></bean>
   ```
4. 在Spring配置文件中配置相关信息
    在上面一下配置过了
5. 测试
    ```
   public class TestUser {
    @Test
    public void TestUserObject(){
        // 加载Spring配置文件，进行对象的创建
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 获取创建的对象
        User user = (User) context.getBean("user");
        // 使用对象调用方法进行测试
        user.add();
    }
    }
   ```

总结：正常情况下我们通过new来创建一个对象，但是使用spring，我们就可以在不new的情况下通过配置文件创建一个对象
，这是因为底层spring用了反射的方法。

### 几个问题

1. 无参构造函数有没有执行？

   执行了。

2. 不用 new 的方式还可以用什么方式创建对象

   反射。

   * 如何使用反射创建对象

     1. 加载bean.XML配置文件

     2. 对xml文件进行解析操作

     3. 获取xml文件bean标签属性值（id属性值和class属性值）

     4. 使用反射根据类全路径创建对象

        ```java
        Class clazz = Class.forName("org.zxb.User");
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        user.add();
        ```

3. 创建的对象放到哪里？

   Map<String, BeanDefinition>

   key : 唯一标识

   value : 类的定义（描述信息） 

## 日志

使用 Log4j2 。Log4j2是开源的日志记录组件，使用广泛，可以代替 System.out 等打印语句。

Log4j2的几个重要部分

1. 日志信息的优先级，从高到底有FATAL > ERROR > WARN > INFO > DEBUGE > TRACE

   FATAL ： 严重错误

   ERROR ：输出错误信息

   WARN : 警告

   INFO ： 输出重要的信息

   DEBUGE : 调试，一般开发中将其设置为最低的开发级别

   TRACE ：追踪

2. 日志信息的输出目的地 ：控制台或者文件中。
3. 日志信息的输出格式 ：控制了日志信息的显示内容。



引入Log4j2的依赖

进入到 log4j2 官网，根据官网提示引入依赖。（项目中引入的依赖和这个不一样，具体可以看视频中介绍）

![image-20230627190300858](https://raw.githubusercontent.com/faceItCalmly/image_host/master/image-20230627190300858.png)

然后配置log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <loggers>
        <!--
            level指定日志级别，从低到高的优先级：
                TRACE < DEBUG < INFO < WARN < ERROR < FATAL
                trace：追踪，是最低的日志级别，相当于追踪程序的执行
                debug：调试，一般在开发中，都将其设置为最低的日志级别
                info：信息，输出重要的信息，使用较多
                warn：警告，输出警告的信息
                error：错误，输出错误信息
                fatal：严重错误
        -->
        <root level="DEBUG">
            <appender-ref ref="spring6log"/>
            <appender-ref ref="RollingFile"/>
            <appender-ref ref="log"/>
        </root>
    </loggers>

    <appenders>
        <!--输出日志信息到控制台-->
        <console name="spring6log" target="SYSTEM_OUT">
            <!--控制日志输出的格式-->
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss SSS} [%t] %-3level %logger{1024} - %msg%n"/>
        </console>

        <!--文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用-->
        <File name="log" fileName="C:/Users/zxb/Desktop/reggie/spring6/test.log" append="false">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"/>
        </File>

        <!-- 这个会打印出所有的信息，
            每次大小超过size，
            则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，
            作为存档-->
        <RollingFile name="RollingFile" fileName="d:/spring6_log/app.log"
                     filePattern="log/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d{yyyy-MM-dd 'at' HH:mm:ss z} %-5level %class{36} %L %M - %msg%xEx%n"/>
            <SizeBasedTriggeringPolicy size="50MB"/>
            <!-- DefaultRolloverStrategy属性如不设置，
            则默认为最多同一文件夹下7个文件，这里设置了20 -->
            <DefaultRolloverStrategy max="20"/>
        </RollingFile>
    </appenders>
</configuration>
```



## IOC

Inversion of Control 的简写翻译为”控制反转“



Spring通过IoC容器来管理所有Java对象的实例化和初始化，控制对象和对象之间的依赖关系。我们将由IoC容器管理的对象称为Spring Bean， 它与使用关键字new创建的 Java对象没有任何区别。



控制反转，反转的是什么？

1. 将对象的创建权利交出去，交给第三方容器负责。
2. 将对象和对象之间关系的维护权交出去，交给第三方容易负责。

控制反转的思想如何实现？

​	DI（Dependency Injection） : 依赖注入



![image-20230627201448513](https://raw.githubusercontent.com/faceItCalmly/image_host/master/image-20230627201448513.png)