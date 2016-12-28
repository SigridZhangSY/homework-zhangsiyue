package helper;

import javax.inject.Inject;

public class InjectClass {

    @Inject
    private SimpleClass injectFiled;

    @Inject
    public InjectClass(SimpleClass injectFiled) {
        this.injectFiled = injectFiled;
    }

    @Inject
    public void setInjectFiled(){

    }

    public SimpleClass getInjectFiled() {
        return injectFiled;
    }
}
