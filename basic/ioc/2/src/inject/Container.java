package inject;

import inject.injector.ConstructorInjector;
import inject.injector.FieldInjector;
import inject.injector.Injector;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Container {
    Injector constructorInjector = new ConstructorInjector();
    Injector fieldInjector = new FieldInjector();

    public List<Injector> resolve(Class<?> type) {
        List<Injector> injectors = new ArrayList<>();
        List<Constructor> injectedConstructors = asList(type.getConstructors())
                .stream()
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        if (injectedConstructors.size() > 0) {
            if (injectedConstructors.size() == 1)
                injectors.add(constructorInjector);
            else
                throw new RuntimeException();
        }

        if (asList(type.getDeclaredFields())
                .stream()
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .findAny()
                .isPresent())
            injectors.add(fieldInjector);

        return injectors;
    }
}
