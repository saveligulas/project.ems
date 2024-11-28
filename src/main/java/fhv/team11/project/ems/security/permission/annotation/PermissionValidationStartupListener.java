package fhv.team11.project.ems.security.permission.annotation;

import fhv.team11.project.ems.security.permission.IPermissionEnum;
import jakarta.annotation.PostConstruct;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissionValidationStartupListener {

    private static final Logger logger = LoggerFactory.getLogger(PermissionValidationStartupListener.class);

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();

        validatePermissions(applicationContext);
    }

    private void validatePermissions(ApplicationContext applicationContext) {
        Reflections reflections = new Reflections("fhv.team11.project.ems");
        Set<Class<? extends IPermissionEnum>> permissionEnumClasses =
                reflections.getSubTypesOf(IPermissionEnum.class);

        Set<String> validPermissionNames = permissionEnumClasses.stream()
                .flatMap(enumClass -> Arrays.stream(enumClass.getEnumConstants())
                        .map(IPermissionEnum::getPermissionName))
                .collect(Collectors.toSet());

        Set<String> invalidPermissions = new HashSet<>();

        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            try {
                Object bean = applicationContext.getBean(beanName);
                Class<?> beanClass = bean.getClass();

                RequiresPermission classAnnotation = AnnotationUtils.findAnnotation(beanClass, RequiresPermission.class);
                if (classAnnotation != null) {
                    invalidPermissions.addAll(validatePermissionAnnotation(classAnnotation, validPermissionNames, beanClass));
                }

                for (Method method : beanClass.getDeclaredMethods()) {
                    RequiresPermission methodAnnotation = AnnotationUtils.findAnnotation(method, RequiresPermission.class);
                    if (methodAnnotation != null) {
                        invalidPermissions.addAll(validatePermissionAnnotation(methodAnnotation, validPermissionNames, method));
                    }
                }
            } catch (Exception e) {
                logger.warn("Error processing bean {}: {}", beanName, e.getMessage());
            }
        }

        if (!invalidPermissions.isEmpty()) {
            String errorMessage = "Invalid permissions detected: " + invalidPermissions;
            logger.error(errorMessage);
            throw new InvalidPermissionConfigurationException(errorMessage);
        }

        logger.info("Permission validation completed successfully");
    }

    private Set<String> validatePermissionAnnotation(RequiresPermission annotation, Set<String> validPermissionNames, Object annotatedElement) {
        Set<String> invalidPermissions = new HashSet<>();
        for (String permissionName : annotation.value()) {
            if (!validPermissionNames.contains(permissionName)) {
                String location = annotatedElement instanceof Class
                        ? "class " + ((Class<?>) annotatedElement).getName()
                        : "method " + ((Method) annotatedElement).getName() +
                        " in class " + ((Method) annotatedElement).getDeclaringClass().getName();

                logger.error("Invalid permission '{}' found in {}", permissionName, location);
                invalidPermissions.add(permissionName);
            }
        }
        return invalidPermissions;
    }

    public static class InvalidPermissionConfigurationException extends RuntimeException {
        public InvalidPermissionConfigurationException(String message) {
            super(message);
        }
    }
}

