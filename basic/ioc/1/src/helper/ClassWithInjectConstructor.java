package helper;

import javax.inject.Inject;

public class ClassWithInjectConstructor implements ClassWithInjectFieldInterface {

    private SimpleInterface injectField;

    @Inject
    public ClassWithInjectConstructor(SimpleInterface injectField) {
        this.injectField = injectField;
    }


    @Override
    public SimpleInterface getInjectField() {
        return injectField;
    }
}
