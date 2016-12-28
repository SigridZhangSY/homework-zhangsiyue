package inject.injector;


import helper.InjectClass;
import helper.SimpleClass;
import org.junit.Test;

import javax.inject.Inject;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
}