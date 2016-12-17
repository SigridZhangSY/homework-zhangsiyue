package container.injector;

import container.Container;

import javax.inject.Inject;
import java.lang.reflect.Field;

public class FieldInjector<V> {
    public void inject(V instance, Container container) throws Exception {
        Field[] fields = instance.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(Inject.class)){
                field.setAccessible(true);
                field.set(instance, container.resolve(field.getType()));
                field.setAccessible(false);
            }
        }
    }
}
