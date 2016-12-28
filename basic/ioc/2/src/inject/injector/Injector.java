package inject.injector;

public interface Injector<T> {
    Class<?>[] required();
    T execute(T target, Object[] dependencies);
}
