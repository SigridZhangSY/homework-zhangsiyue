package container;

import helper.*;
import org.junit.Before;
import org.junit.Test;
import org.omg.CosNaming.NameComponent;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class ContainerTest {

    private Container container;

    @Before
    public void setUp() throws Exception {
        container = new Container();

    }

    @Test
    public void should_bind_interface_and_implement_and_get_instance() throws Exception {
        container.bind(SimpleInterface.class, SimpleClass.class);
        Object object = container.resolve(SimpleInterface.class);

        assertThat(object, is(notNullValue()));

        assertThat((object instanceof SimpleInterface), is(true));
    }

    @Test
    public void should_not_bind_interface_to_not_implement_class() {
        try {
            container.bind(SimpleInterface.class, OtherSimpleClass.class);
            fail();
        } catch (Exception e) {
            assertThat(e, is(notNullValue()));
        }
    }

    @Test
    public void should_return_null_when_resolve_not_bind_class() throws Exception {
        Object object = container.resolve(SimpleInterface.class);

        assertThat(object, is(nullValue()));
    }

    @Test
    public void should_resolve_class_container_instantiated() throws Exception {
        container.bind(SimpleInterface.class, SimpleClass.class);
        Object object = container.resolve(SimpleInterface.class);

        assertThat(container.resolve(SimpleInterface.class), is(notNullValue()));
    }

    @Test
    public void should_inject_class_with_inject_field() throws Exception {
        container.bind(SimpleInterface.class, SimpleClass.class);
        container.bind(ClassWithInjectFieldInterface.class, ClassWithInjectField.class);

        ClassWithInjectFieldInterface object = (ClassWithInjectFieldInterface)container.resolve(ClassWithInjectFieldInterface.class);
        assertThat(object, is(notNullValue()));
        assertThat(object.getInjectField(), is(notNullValue()));
    }

    @Test
    public void should_inject_class_with_inject_constructor() throws Exception {
        container.bind(SimpleInterface.class, SimpleClass.class);
        container.bind(ClassWithInjectFieldInterface.class, ClassWithInjectConstructor.class);

        ClassWithInjectFieldInterface object = (ClassWithInjectFieldInterface)container.resolve(ClassWithInjectFieldInterface.class);
        assertThat(object, is(notNullValue()));
        assertThat(object.getInjectField(), is(notNullValue()));
    }

    @Test
    public void should_not_resole_class_with_more_than_one_inject_constructor() throws Exception {
        container.bind(SimpleInterface.class, SimpleClass.class);
        container.bind(ClassWithInjectFieldInterface.class, ClassWithSeveralInjectConstructor.class);

        try {
            container.resolve(ClassWithInjectFieldInterface.class);
            fail();
        } catch (Exception e) {
            assertThat(e, is(notNullValue()));
        }
    }
}