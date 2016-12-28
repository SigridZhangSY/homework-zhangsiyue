package inject.injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorInjector<T> implements Injector<T> {

    private Constructor injectConstructor;

    public ConstructorInjector(Constructor injectConstructor) {
        this.injectConstructor = injectConstructor;
    }

    @Override
    public Class<?>[] required() {
        return new Class<?>[0];
    }

    @Override
    public T execute(T target, Object[] dependencies) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T)injectConstructor.newInstance(dependencies);
    }


    public Constructor getInjectConstructor() {
        return injectConstructor;
    }
}
