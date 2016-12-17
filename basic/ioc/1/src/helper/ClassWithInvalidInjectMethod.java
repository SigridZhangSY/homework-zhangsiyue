package helper;

import javax.inject.Inject;

public class ClassWithInvalidInjectMethod implements ClassWithInjectFieldInterface {

    private SimpleInterface injectField;

    @Inject
    public void invalidInjectMethod(SimpleInterface injectField) {
        this.injectField = injectField;
    }

    @Override
    public SimpleInterface getInjectField() {
        return injectField;
    }
}