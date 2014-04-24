package me.knocks.amulib.tasks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An internal method @interface specifically designed for internal tasks to be able to handle objects easily.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InternalTask {
    Class<?> getType();
}
