package fhv.team11.project.ems.commons.validation.model;

public interface IModelAttributeName {
    default String getModelAttributeName() {
        return getModelAttributeName(this.getClass());
    }

    static String getModelAttributeName(Class<?> clazz) {
        String className = clazz.getSimpleName();
        if (className.endsWith("DTO")) {
            className = className.substring(0, className.length() - 3);
        }
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }
}
