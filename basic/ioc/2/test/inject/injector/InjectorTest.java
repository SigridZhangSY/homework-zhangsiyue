package inject.injector;


import helper.InjectClass;
import helper.SimpleClass;
import org.junit.Test;

import javax.inject.Inject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class InjectorTest {
    @Test
    public void should_set_dependencies_through_constuctor() throws Exception {
        Class type = InjectClass.class;
        ConstructorInjector injector = new ConstructorInjector(
                asList(type.getConstructors())
                .stream()
                .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                .findAny()
                .get()
        );
        Object[] dependencies = {new SimpleClass()};
        InjectClass target = (InjectClass) injector.execute(null, dependencies);
        assertThat(target.getInjectFiled(), is(notNullValue()));
    }

    @Test
    public void should_set_dependencies_through_field() throws Exception {
        Class type = InjectClass.class;

        List<Field> fields = asList(type.getDeclaredFields())
                .stream()
                .filter(field -> field.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList());
        List<FieldInjector> injectors = new ArrayList<>();
        fields.forEach(field -> {
            injectors.add(new FieldInjector(field));
        });
        Object[] dependencies = {new SimpleClass()};
        InjectClass target = new InjectClass();

        assertThat(target.getInjectFiled(), is(nullValue()));

        injectors.forEach(injector -> {
            try {
                injector.execute(target, dependencies);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        assertThat(target.getInjectFiled(), is(notNullValue()));
    }
}