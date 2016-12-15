package container;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Container<K,V> {

    Map<Class<K>, V > dictionary = new HashMap<>();

    public void bind(Class<K> interfaceType, Class<V> implementType) throws Exception{
        Class[] interfaces = implementType.getInterfaces();
        if (Arrays.asList(interfaces).contains(interfaceType))
            dictionary.put(interfaceType, (V)implementType.newInstance());
        else
            throw new Exception();
    }

    public V resolve(Class<K> type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        V implement = dictionary.get(type);
        if(implement != null)
            return implement;
        else
            return null;
    }
}
