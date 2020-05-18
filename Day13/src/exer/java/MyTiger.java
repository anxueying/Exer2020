package exer.java;



//@SuppressWarnings()  -->  target

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@Target({METHOD})//TYPE类，FIELD属性，CONSTRUCTOR构造器，PARAMETER  LOCAL_VARIABLE
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTiger {
    String value() default "";
}
