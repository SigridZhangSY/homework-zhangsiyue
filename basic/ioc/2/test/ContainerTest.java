import helper.InjectClass;
import inject.Container;
import inject.injector.ConstructorInjector;
import inject.injector.Injector;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
}
