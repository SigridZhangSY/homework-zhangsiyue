package inject;

import inject.injector.ConstructorInjector;
import inject.injector.Injector;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Container {
    Injector constructorInjector = new ConstructorInjector();

    public List<Injector> resolve(Class<?> type){
        List<Injector> injectors = new ArrayList<>();
        List<Constructor> injectedConstructors = Arrays.asList(type.getConstructors())
                .stream()
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        if(injectedConstructors.size() > 0)
            injectors.add(constructorInjector);

        return injectors;
    }
}
