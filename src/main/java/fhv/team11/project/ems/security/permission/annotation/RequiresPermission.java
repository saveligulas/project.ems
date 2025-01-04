package fhv.team11.project.ems.security.permission.annotation;

import fhv.team11.project.ems.security.permission.IPermissionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermission {
    String[] value();
    boolean allRequired() default true;
}
