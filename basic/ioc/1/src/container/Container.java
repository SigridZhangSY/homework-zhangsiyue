package container;

import java.util.*;

public class Container<K,V> {

    Map<Class<K>, Class<V>>  dictionary = new HashMap<>();
    Set<V> instances = new HashSet<>();

    public void bind(Class<K> interfaceType, Class<V> implementType) throws Exception{

        Class[] interfaces = implementType.getInterfaces();

        if (Arrays.asList(interfaces).contains(interfaceType))
            dictionary.put(interfaceType, implementType);
        else
            throw new Exception();
    }

    public V resolve(Class<K> type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<V> implementType = dictionary.get(type);

        if(implementType != null){
            Optional<V> implement = instances.stream().filter(instance -> instance.getClass() == implementType ).findAny();
            if(!implement.isPresent()){
                implement = Optional.of(implementType.newInstance());
                instances.add(implement.get());
            }
            return implement.get();
        }
        return null;
    }
}
