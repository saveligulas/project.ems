package fhv.team11.project.ems.commons.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DomainBuilderProcessor {
    public static class DomainObjectBuilder<T extends IDomainObject> {
        private final T instance;

        private DomainObjectBuilder(Class<T> domainClass) {
            try {
                Constructor<T> constructor = domainClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                this.instance = constructor.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Could not create domain object", e);
            }
        }

        public T build() {
            instance.validate();
            return instance;
        }
    }

    public static <T extends IDomainObject> DomainObjectBuilder<T> builder(Class<T> domainClass) {
        return new DomainObjectBuilder<>(domainClass);
    }
}