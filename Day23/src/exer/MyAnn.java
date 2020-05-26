package exer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnn{
    String value() default "xiaolongge";
}
