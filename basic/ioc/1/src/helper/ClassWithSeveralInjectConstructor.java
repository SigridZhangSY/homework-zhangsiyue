package helper;

import javax.inject.Inject;

public class ClassWithSeveralInjectConstructor implements ClassWithInjectFieldInterface {
    private SimpleInterface injectField;

    @Inject
    public ClassWithSeveralInjectConstructor(SimpleInterface injectField) {
        this.injectField = injectField;
    }

    @Inject
    public ClassWithSeveralInjectConstructor() {
    }

    @Override
    public SimpleInterface getInjectField() {
        return injectField;
    }
}
