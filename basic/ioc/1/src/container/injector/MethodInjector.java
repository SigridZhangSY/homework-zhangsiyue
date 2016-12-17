package container.injector;

import container.Container;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodInjector<V> {

    public void inject(V instance, Container container) throws Exception {
        List<Method> injectMethods = Arrays.asList(instance.getClass().getMethods())
                .stream()
                .filter(method -> method.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());

        for (Method method : injectMethods) {
            if (!method.getName().startsWith("set"))
                throw new Exception();
            List<Object> parameters = new ArrayList<>();
            for (Class parameterType : method.getParameterTypes())
                parameters.add(container.resolve(parameterType));

            method.invoke(instance, parameters.toArray());
        }

    }
}
