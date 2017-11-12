package edu.hnust.application.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryUtil implements ApplicationContextAware {
    private static ApplicationContext ac;
    
    private BeanFactoryUtil() {
        
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException {
        ac = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext() {
        
        return ac;
    }
}
