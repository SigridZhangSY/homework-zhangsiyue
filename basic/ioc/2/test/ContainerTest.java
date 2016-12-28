import helper.ClassWithTwoInjectConstructor;
import helper.InjectClass;
import inject.Container;
import inject.injector.ConstructorInjector;
import inject.injector.FieldInjector;
import inject.injector.Injector;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

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
        List<Injector> injectors = container.resolve(InjectClass.class);

        Optional<Injector> constructorInjector = injectors
                .stream()
                .filter(injector -> injector instanceof FieldInjector)
                .findAny();

        assertThat(constructorInjector.isPresent(), is(true));

    }
}
