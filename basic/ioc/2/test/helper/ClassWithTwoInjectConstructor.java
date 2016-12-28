package helper;

import javax.inject.Inject;

public class ClassWithTwoInjectConstructor {
    @Inject
    public ClassWithTwoInjectConstructor() {
    }

    @Inject
    public ClassWithTwoInjectConstructor(int parameter) {
    }
}
