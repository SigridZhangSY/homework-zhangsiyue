package inject;

import inject.injector.ConstructorInjector;
import inject.injector.FieldInjector;
import inject.injector.Injector;
import inject.injector.MethodInjector;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Container {

    public List<Injector> resolve(Class<?> type) {
        List<Injector> injectors = new ArrayList<>();
        List<Constructor> injectedConstructors = asList(type.getConstructors())
                .stream()
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        if (injectedConstructors.size() > 0) {
            if (injectedConstructors.size() == 1)
                injectors.add(new ConstructorInjector(injectedConstructors.get(0)));
            else
                throw new RuntimeException();
        }

        List<Field> fields = asList(type.getDeclaredFields())
                .stream()
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        fields.forEach(field -> {
            injectors.add(new FieldInjector(field));
        });

        List<Method> methods = asList(type.getMethods())
                .stream()
                .filter(method -> method.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        methods.forEach(method -> {
            injectors.add(new MethodInjector(method));
        });

        return injectors;
    }
}
