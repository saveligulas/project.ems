package fhv.team11.project.ems.commons.error;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class<?> entityClass, Object id) {
        super(String.format("Entity of type %s with id %s not found", entityClass.getSimpleName(), id));
    }
}
