package fhv.team11.project.ems.security.permission.annotation;

import fhv.team11.project.ems.security.permission.IPermissionEnum;
import fhv.team11.project.ems.security.permission.UserPermission;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("fhv.team11.project.ems.security.permission.annotation.RequiresPermission")
public class RequiresPermissionProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(RequiresPermission.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(RequiresPermission.class)) {
            RequiresPermission annotation = element.getAnnotation(RequiresPermission.class);
            for (String permissionName : annotation.value()) {
                boolean validPermission = false;

                validPermission |= checkEnumValid(permissionName, UserPermission.class);

                if (!validPermission) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "Invalid permission: " + permissionName, element);
                }
            }
        }
        return false;
    }

    private boolean checkEnumValid(String permissionName, Class<? extends Enum<? extends IPermissionEnum>> enumClass) {
        for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
            if (((IPermissionEnum) enumConstant).getPermissionName().equals(permissionName)) {
                return true;
            }
        }
        return false;
    }
}