package container;

import helper.SimpleClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class ContainerTest {
    @Test
    public void should_get_instance_of_simple_class() throws Exception {
        Container container = new Container();

        Object object = container.resolve(SimpleClass.class);

        assertThat(object, is(notNullValue()));
    }
}