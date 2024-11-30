package fhv.team11.project.ems.security.permission.annotation;

import fhv.team11.project.ems.security.jwt.JwtSecurityContextHolder;
import fhv.team11.project.ems.security.permission.GrantedPermission;
import fhv.team11.project.ems.security.permission.role.Role;
import fhv.team11.project.ems.security.permission.role.RolePermissions;
import fhv.team11.project.ems.user.entity.UserJDBC;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class RequiresPermissionAspect {
    @Before("@annotation(RequiresPermission)")
    public void checkPermissions(org.aspectj.lang.JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        RequiresPermission annotation = method.getAnnotation(RequiresPermission.class);
        if (annotation == null) {
            return;
        }

        List<GrantedPermission> requiredPermissions = Arrays.stream(annotation.value())
                .map(GrantedPermission::of)
                .toList();
        boolean allRequired = annotation.allRequired();

        UserJDBC userJDBC = JwtSecurityContextHolder.getUserJDBC();
        List<Role> userRoles = userJDBC.getRoles();
        List<GrantedPermission> userPermissions = userRoles.stream()
                .map(RolePermissions::getPermissions)
                .flatMap(List::stream)
                .toList();

        if (allRequired) {
            if (!userPermissions.containsAll(requiredPermissions)) {
                throw new SecurityException("User does not have required permissions: " + requiredPermissions);
            }
        } else {
            if (requiredPermissions.stream().noneMatch(userPermissions::contains)) {
                throw new SecurityException("User does not have any of the required permissions: " + requiredPermissions);
            }
        }
    }
}
