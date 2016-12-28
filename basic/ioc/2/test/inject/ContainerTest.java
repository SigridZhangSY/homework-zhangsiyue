package inject;

import helper.ClassWithTwoInjectConstructor;
import helper.InjectClass;
import inject.injector.ConstructorInjector;
import inject.injector.FieldInjector;
import inject.injector.Injector;
import inject.injector.MethodInjector;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ContainerTest {
    Container container;

    @Before
    public void setUp() throws Exception {
        container = new Container();
    }

    @Test
    public void should_create_constructor_injector_for_class_with_injected_constructor() throws Exception {
        List<Injector> injectors = container.resolve(InjectClass.class);

        Optional<Injector> constructorInjector = injectors
                .stream()
                .filter(injector -> injector instanceof ConstructorInjector)
                .findAny();

        assertThat(constructorInjector.isPresent(), is(true));
    }

    @Test
    public void should_not_create_injector_for_class_with_more_than_one_constructor() throws Exception {
        try {
            container.resolve(ClassWithTwoInjectConstructor.class);
            fail();
        } catch (Exception e) {
            assertThat(e instanceof RuntimeException, is(true));
        }

    }

    @Test
    public void should_create_injector_for_injected_filed() throws Exception {
        Class type = InjectClass.class;

        List<Injector> injectors = container.resolve(type);

        List<Injector> fieldInjectors = injectors
                .stream()
                .filter(injector -> injector instanceof FieldInjector)
                .collect(Collectors.toList());

        asList(type.getDeclaredFields()).forEach(field -> {
            assertThat(fieldInjectors
                            .stream()
                            .filter(injector -> ((FieldInjector) injector).getInjectField().equals(field))
                            .findAny()
                            .isPresent(),
                    is(true));
        });
    }

    @Test
    public void should_create_injector_for_injected_method() throws Exception {
        Class type = InjectClass.class;

        List<Injector> injectors = container.resolve(type);

        List<Injector> methodInjectors = injectors
                .stream()
                .filter(injector -> injector instanceof MethodInjector)
                .collect(Collectors.toList());

        asList(type.getMethods())
                .stream()
                .filter(method -> method.isAnnotationPresent(Inject.class))
                .collect(Collectors.toList())
                .forEach(method -> {
                    assertThat(methodInjectors
                                    .stream()
                                    .filter(injector -> ((MethodInjector) injector).getInjectMethod().equals(method))
                                    .findAny()
                                    .isPresent(),
                            is(true));
                });
    }
}
