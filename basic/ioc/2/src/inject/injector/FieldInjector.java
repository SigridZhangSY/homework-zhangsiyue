package inject.injector;

import java.lang.reflect.Field;

public class FieldInjector implements Injector {
    private Field injectField;

    public FieldInjector(Field injectField) {
        this.injectField = injectField;
    }

    @Override
    public Class<?>[] required() {
        return new Class<?>[0];
    }

    @Override
    public Object execute(Object target, Object[] dependencies) {
        return null;
    }

    public Field getInjectField() {
        return injectField;
    }
}
