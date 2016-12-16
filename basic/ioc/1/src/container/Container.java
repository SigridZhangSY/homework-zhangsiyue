package container;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class Container<K,V> {

    private Map<Class<K>, Class<V>>  dictionary = new HashMap<>();
    private Set<V> instances = new HashSet<>();

    public void bind(Class<K> interfaceType, Class<V> implementType) throws Exception{

        Class[] interfaces = implementType.getInterfaces();

        if (Arrays.asList(interfaces).contains(interfaceType))
            dictionary.put(interfaceType, implementType);
        else
            throw new Exception();
    }

    public V resolve(Class<?> type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<V> implementType = dictionary.get(type);

        if(implementType != null){
            Optional<V> implement = instances.stream().filter(instance -> instance.getClass() == implementType ).findAny();
            if(!implement.isPresent()){
                implement = Optional.of(implementType.newInstance());
                Field[] fields = implement.get().getClass().getDeclaredFields();
                for(Field field : fields){
                    if(field.isAnnotationPresent(Inject.class)){
                        field.setAccessible(true);
                        field.set(implement.get(), resolve(field.getType()));
                        field.setAccessible(false);
                    }
                }
                instances.add(implement.get());
            }
            return implement.get();
        }
        return null;
    }
}
