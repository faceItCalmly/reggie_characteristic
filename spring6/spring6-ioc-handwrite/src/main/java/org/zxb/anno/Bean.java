package org.zxb.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 注解能在哪里用（类或者接口上能用）
@Retention(RetentionPolicy.RUNTIME) // 注解的作用范围（运行时生效）
public @interface Bean {
}
