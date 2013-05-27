package controller;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyWebApplication extends WebApplication implements ApplicationContextAware {

    boolean isInitialized = false;

    @Override
    protected void init() {
        if (!isInitialized) {
            super.init();
            setListeners();
            isInitialized = true;
        }
    }

    private void setListeners() {
//        addComponentInstantiationListener(new SpringComponentInjector(this, context));
        super.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }

    @Override
    public Class getHomePage() {

        return IndexPage.class;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        // Not used as of Wicket 1.4
    }


}


