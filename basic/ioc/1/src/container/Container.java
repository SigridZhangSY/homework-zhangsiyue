package container;

public class Container {

    public <T> T resolve(Class<T> type) throws ClassNotFoundException {
        String className = type.getName();
        return (T)Class.forName(className);
    }
}
