package fhv.team11.project.ems.commons.model;

public interface IModelAttributeName {
    default String getModelAttributeName() {
        String className = this.getClass().getSimpleName();
        if (className.endsWith("DTO")) {
            className = className.substring(0, className.length() - 3);
        }
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }
}
