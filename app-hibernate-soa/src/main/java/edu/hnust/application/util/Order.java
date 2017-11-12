package edu.hnust.application.util;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;    
    private boolean ascending;    
    private boolean ignoreCase;    
    private String propertyName;
    
    public Order() {
    }
    
    public static Order asc(String propertyName) {
        return new Order(propertyName, true);
    }
    
    public static Order desc(String propertyName) {
        return new Order(propertyName, false);
    }
    
    protected Order(String propertyName, boolean ascending) {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}