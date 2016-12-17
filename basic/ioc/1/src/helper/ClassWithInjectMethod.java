package helper;

import javax.inject.Inject;

public class ClassWithInjectMethod implements ClassWithInjectFieldInterface {

    private SimpleInterface injectField;

    @Inject
    public void setInjectField(SimpleInterface injectField) {
        this.injectField = injectField;
    }

    @Override
    public SimpleInterface getInjectField() {
        return injectField;
    }
}
