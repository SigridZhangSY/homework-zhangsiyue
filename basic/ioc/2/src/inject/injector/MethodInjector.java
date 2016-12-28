package inject.injector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInjector implements Injector{

    private Method injectMethod;

    public MethodInjector(Method injectMethod) {
        this.injectMethod = injectMethod;
    }

    @Override
    public Class<?>[] required() {
        return new Class<?>[0];
    }

    @Override
    public Object execute(Object target, Object[] dependencies) throws InvocationTargetException, IllegalAccessException {
        injectMethod.invoke(target, dependencies);
        return target;
    }

    public Method getInjectMethod() {
        return injectMethod;
    }
}
