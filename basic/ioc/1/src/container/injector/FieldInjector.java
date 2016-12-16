package container.injector;

import container.Container;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Optional;

public class FieldInjector<V> {
    public void inject(Optional<V> implement, Container container) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Field[] fields = implement.get().getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(Inject.class)){
                field.setAccessible(true);
                field.set(implement.get(), container.resolve(field.getType()));
                field.setAccessible(false);
            }
        }
    }
}
