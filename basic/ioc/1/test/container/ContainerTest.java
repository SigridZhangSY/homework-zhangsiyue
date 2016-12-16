package container;

import helper.OtherSimpleClass;
import helper.SimpleClass;
import helper.SimpleInterface;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class ContainerTest {

    @Test
    public void should_bind_interface_and_implement_and_get_instance() throws Exception {
        Container container = new Container();

        container.bind(SimpleInterface.class, SimpleClass.class);
        Object object = container.resolve(SimpleInterface.class);

        assertThat(object, is(notNullValue()));

        assertThat((object instanceof SimpleInterface), is(true));
    }

    @Test
    public void should_not_bind_interface_to_not_implement_class() {
        Container container = new Container();
        try {
            container.bind(SimpleInterface.class, OtherSimpleClass.class);
            fail();
        } catch (Exception e) {
            assertThat(e, is(notNullValue()));
        }
    }

    @Test
    public void should_return_null_when_resolve_not_bind_class() throws Exception {
        Container container = new Container();

        Object object = container.resolve(SimpleInterface.class);
        assertThat(object, is(nullValue()));
    }
}