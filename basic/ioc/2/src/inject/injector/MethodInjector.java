package inject.injector;

public class MethodInjector implements Injector{

    @Override
    public Class<?>[] required() {
        return new Class<?>[0];
    }

    @Override
    public Object execute(Object target, Object[] dependencies) {
        return null;
    }
}
