package inject.injector;

import java.lang.reflect.Constructor;

public class ConstructorInjector implements Injector {

    private Constructor injectConstructor;

    public ConstructorInjector(Constructor injectConstructor) {
        this.injectConstructor = injectConstructor;
    }

    @Override
    public Class<?>[] required() {
        return new Class<?>[0];
    }

    @Override
    public Object execute(Object target, Object[] dependencies) {
        return null;
    }

    public Constructor getInjectConstructor() {
        return injectConstructor;
    }
}
