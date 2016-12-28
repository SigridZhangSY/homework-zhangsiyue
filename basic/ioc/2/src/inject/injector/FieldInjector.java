package inject.injector;

import java.lang.reflect.Field;

public class FieldInjector<T> implements Injector<T> {
    private Field injectField;

    public FieldInjector(Field injectField) {
        this.injectField = injectField;
    }

    @Override
    public Class<?>[] required() {
        return new Class<?>[0];
    }

    @Override
    public T execute(T target, Object[] dependencies) throws IllegalAccessException {
        injectField.setAccessible(true);
        injectField.set(target, dependencies[0]);
        injectField.setAccessible(false);
        return target;
    }

    public Field getInjectField() {
        return injectField;
    }
}
