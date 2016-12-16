package helper;

import javax.inject.Inject;

public class ClassWithInjectField implements ClassWithInjectFieldInterface{
    @Inject
    private SimpleInterface injectField;

    public SimpleInterface getInjectField() {
        return injectField;
    }
}
