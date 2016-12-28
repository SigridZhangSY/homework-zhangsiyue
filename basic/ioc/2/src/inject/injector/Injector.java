package inject.injector;

import java.lang.reflect.InvocationTargetException;

public interface Injector<T> {
    Class<?>[] required();
    T execute(T target, Object[] dependencies) throws IllegalAccessException, InvocationTargetException, InstantiationException;
}
