package container;

import java.util.HashMap;
import java.util.Map;

public class Container {

    Map<Class, Class > dictionary = new HashMap<>();

    public <K,V> void bind(Class<K> interfaceType, Class<V> implementType){
        dictionary.put(interfaceType, implementType);
    }

    public <T> T resolve(Class<T> type) throws ClassNotFoundException {
        Class implement = dictionary.get(type);
        if(implement != null)
            return (T) Class.forName(implement.getName());

        return (T)Class.forName(type.getName());
    }
}
