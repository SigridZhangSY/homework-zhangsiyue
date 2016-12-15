package container;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Container {

    Map<Class, Class > dictionary = new HashMap<>();

    public <K,V> void bind(Class<K> interfaceType, Class<V> implementType) throws Exception{
        Class[] interfaces = implementType.getInterfaces();
        if (Arrays.asList(interfaces).contains(interfaceType))
            dictionary.put(interfaceType, implementType);
        else
            throw new Exception();
    }

    public <T> T resolve(Class<T> type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class implement = dictionary.get(type);
        if(implement != null)
            return (T)implement.newInstance();
        else
            return (T)type.newInstance();
    }
}
