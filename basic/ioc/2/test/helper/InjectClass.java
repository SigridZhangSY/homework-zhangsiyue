package helper;

import javax.inject.Inject;

public class InjectClass {

    @Inject
    private SimpleClass injectFiled;

    @Inject
    public InjectClass(SimpleClass injectFiled) {
        this.injectFiled = injectFiled;
    }

    public InjectClass(){}

    @Inject
    public void setInjectFiled(SimpleClass injectFiled){
        this.injectFiled = injectFiled;
    }

    public SimpleClass getInjectFiled() {
        return injectFiled;
    }
}
