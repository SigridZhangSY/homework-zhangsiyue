package container.injector;

import container.Container;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructorInjector<V> {

    public V inject(Class<V> implementType, Container container) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        List<Constructor> injectedConstructors = Arrays.asList(implementType.getConstructors())
                .stream()
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());


        if(injectedConstructors.size() == 1) {
            Constructor constructor = injectedConstructors.get(0);
            List<Object> parameters = new ArrayList<>();
            for (Class parameterType : constructor.getParameterTypes())
                parameters.add(container.resolve(parameterType));

            return (V) constructor.newInstance(parameters.toArray());
        }
        return implementType.newInstance();
    }
}
